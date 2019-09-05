package lk.microservices.auth.server.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//	@Value("${security.jwt.client-id}")
//	private String clientId;
//
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
//
//	@Value("${security.jwt.grant-type}")
//	private String grantType;
//
//	@Value("${security.jwt.scope-read}")
//	private String scopeRead;
//
//	@Value("${security.jwt.scope-write}")
//	private String scopeWrite = "write";
//
//	@Value("${security.jwt.resource-ids}")
//	private String resourceIds;

	@Value("${security.signing-key}")
	private String signingKey;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService customUserDetailsService;

	private Map<String, String> additionalInformation;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer
		        .inMemory()
		        .withClient("testjwtclientid")
		        .secret(passwordEncoder.encode("XY7kmzoNzl100"))
		        .authorizedGrantTypes("password", "refresh_token")
		        .scopes("read", "write")
				.accessTokenValiditySeconds(1500)
				.refreshTokenValiditySeconds(28800)
		        .resourceIds("testjwtresourceid")
				.and()
				.withClient("testjwtclientid2")
				.secret(passwordEncoder.encode("XY7kmzoNzl100"))
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("read", "write")
				.accessTokenValiditySeconds(20)
				.refreshTokenValiditySeconds(40)
				.resourceIds("testjwtresourceid2");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter));
		endpoints
				.tokenStore(tokenStore)
				.tokenEnhancer(tokenEnhancerChain)
				.authenticationManager(authenticationManager)
				.userDetailsService(customUserDetailsService);
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomAccessTokenEnhancer();
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	@Primary
	//Making this primary to avoid any accidental duplication with another token service instance of the same name
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}
}
