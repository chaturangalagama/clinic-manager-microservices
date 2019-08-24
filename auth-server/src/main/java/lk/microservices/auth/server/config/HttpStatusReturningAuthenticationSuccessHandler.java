package lk.microservices.auth.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.microservices.auth.server.model.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roshane on 1/29/2017.
 */
@Component("authenticationSuccessHandler")
public class HttpStatusReturningAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.debug("Authentication success returning HttpStatus 200");
        response.setStatus(HttpStatus.OK.value());
        writeUserDetailsToResponse(response, authentication.getPrincipal());
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
    }

    private void writeUserDetailsToResponse(HttpServletResponse response, Object authDetails) {
        StringWriter stringWriter = new StringWriter();
        //TODO add the csrf token
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userDetails", authDetails);
            AuthResponse<Map<String, Object>> authResponse = new AuthResponse<>(params, HttpStatus.OK);
            objectMapper.writeValue(stringWriter, authResponse);
            response.getWriter().write(stringWriter.toString());
        } catch (IOException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.error("error writing user details {}", e);
        }
    }
}
