package cd.ku6.service;

import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.client.oauth.OAuth;
import com.youzan.open.sdk.client.oauth.OAuthContext;
import com.youzan.open.sdk.client.oauth.OAuthFactory;
import com.youzan.open.sdk.client.oauth.OAuthType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/15.
 */
@Service
@PropertySource("classpath:application.yml")
public class ClientService {
    private static YZClient client;
    @Value("${client.id}")
    private String clientId;
    @Value("${client.screct}")
    private String clientSecret;
    @Value("${shop.id}")
    private String shopId;

    public YZClient getClient() {
        if (client != null && this.validateClient(client)) {
            return client;
        } else {
            OAuth oauth = OAuthFactory.create(OAuthType.SELF, new OAuthContext(
                    this.clientId, this.clientSecret,
                    Long.parseLong(shopId)));
            client = new DefaultYZClient(new Token(oauth.getToken().getAccessToken()));
            return client;
        }
    }

    private boolean validateClient(YZClient client) {
        return true;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}