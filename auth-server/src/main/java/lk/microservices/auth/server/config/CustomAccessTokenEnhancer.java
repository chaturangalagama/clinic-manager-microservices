package lk.microservices.auth.server.config;

import lk.microservices.auth.server.model.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomAccessTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication != null) {
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof CustomUser) {
                Map<String, Object> additionalInfo = new HashMap<>();
                additionalInfo.put("user_details", principal);
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            }
        }
        return accessToken;
    }
}
