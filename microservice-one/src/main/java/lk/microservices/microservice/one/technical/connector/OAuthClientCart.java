package lk.microservices.microservice.one.technical.connector;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class OAuthClientCart {

//    @Autowired
//    private RestTemplate restTemplate;
//
//    public ResponseEntity<Map> testClient() {
//        try {
//            String userpassword =  "testjwtclientid:XY7kmzoNzl100";
//            byte[] bytesEncoded = Base64.encodeBase64(userpassword.getBytes());
//            String userpasswordEncoded = new String(bytesEncoded);
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("username", "john.doe");
//            map.add("grant_type", "password");
//            map.add("password", "123456");
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            headers.set("Authorization", "Basic "+userpasswordEncoded);
//            final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
//            ResponseEntity<Map> responseEntity = restTemplate.exchange("http://localhost:8089/oauth/token", HttpMethod.POST, entity, Map.class);
//            String token = (String) responseEntity.getBody().get("access_token");
//            HttpHeaders headersNew =  new HttpHeaders();
//            headersNew.add("Authorization", "Bearer "+token);
//            HttpEntity reqEntityNew = new HttpEntity(headersNew);
//            ResponseEntity<Map> respNew = restTemplate.exchange("http://localhost:8081/menu", HttpMethod.GET, reqEntityNew, Map.class);
//            System.out.println(respNew);
//            return respNew;
//        } catch (RestClientException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

}
