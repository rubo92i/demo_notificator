package am.basic.notificator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class RemoteConfig {



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


}
