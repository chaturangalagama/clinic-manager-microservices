package lk.microservices.auth.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.microservices.auth.server.model.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;

/**
 * Created by roshane on 1/29/2017.
 */
@Component("authenticationFailureHandler")
public class HttpStatusReturningAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.debug("Authentication failed returning HttpStatus {}",HttpStatus.FORBIDDEN.value());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        writeApiResponse(exception,response);
        response.getWriter().flush();
    }

    private void writeApiResponse(AuthenticationException exception,HttpServletResponse response){
        logger.error("error {}",exception);
        AuthResponse<String> authResponse = new AuthResponse<>(null,HttpStatus.BAD_REQUEST);
        authResponse.setAdditionalParams(Collections.singletonMap("error",exception.getMessage()));
        StringWriter stringWriter=new StringWriter();
        try {
            objectMapper.writeValue(stringWriter,authResponse);
            response.getWriter().write(stringWriter.toString());
        } catch (IOException e) {
            logger.error("error writing authentication failure response {}",e);
        }
    }
}
