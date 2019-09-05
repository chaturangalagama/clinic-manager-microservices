package lk.microservices.auth.server.controller;

import lk.microservices.auth.server.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class UserController {

//    @PostMapping("/login")
//    public AuthResponse login(){
//        return new AuthResponse("Login Success", HttpStatus.OK);
//    }
//
//    @RequestMapping("/user")
//    @ResponseBody
//    public Principal user(Principal user) {
//        return user;
//    }
//
//    @Autowired
//    private TokenStore tokenStore;
//    @Autowired
//    private AuthorizationServerTokenServices authorizationServerTokenServices;
//
//    @PostMapping("/oauth/revoke-token")
//    public AuthResponse logout(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null) {
//            String tokenValue = authHeader.replace("Bearer", "").trim();
//            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//            tokenStore.removeAccessToken(accessToken);
//        }
//        return new AuthResponse("Logout Success", HttpStatus.OK);
//    }
}
