package am.basic.notificator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class RemoteConfig {

    @Value("${spring.ws.oauth.url}")
    private String oauthUrl;

    @Value("${spring.ws.oauth.username}")
    private String oauthUsername;

    @Value("${spring.ws.oauth.password}")
    private String oauthPassword;


    @Value("${spring.ws.oauth.http.url}")
    private String oauthHttpUrl;


    @Bean
    public TokenStore tokenStore() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl(oauthHttpUrl);
        invoker.setServiceInterface(TokenStore.class);
        invoker.afterPropertiesSet();
        return (TokenStore) invoker.getObject();
    }

    /* @Bean
    public TokenStore tokenStore() {
         HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl(oauthUrl);
        invoker.setServiceInterface(TokenStore.class);
        invoker.setHessian2(true);
        invoker.setUsername(oauthUsername);
        invoker.setPassword(oauthPassword);
        invoker.afterPropertiesSet();
        return (TokenStore) invoker.getObject();
    }*/

}
