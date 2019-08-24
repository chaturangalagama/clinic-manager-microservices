package lk.microservices.auth.server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

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
