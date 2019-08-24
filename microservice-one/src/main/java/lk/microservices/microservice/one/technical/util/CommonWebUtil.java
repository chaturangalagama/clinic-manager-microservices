package lk.microservices.microservice.one.technical.util;

import lk.microservices.microservice.one.technical.enums.StatusCode;
import lk.microservices.microservice.one.technical.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

public class CommonWebUtil {

    public static ResponseEntity<ApiResponse> httpApiResponse(ApiResponse api) {
        if (api.getStatusCode() == StatusCode.S0000) {
            return ResponseEntity.status(HttpStatus.OK).body(api);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(api);
        }
    }

    public static List<String> userRolesAsString(Principal principal) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        return authenticationToken.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
