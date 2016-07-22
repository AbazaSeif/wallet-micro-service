package wallet.validators.jwt;

import wallet.exceptions.WalletException;
import wallet.validators.Validator;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 03-07-2016.
 */

@Component
public class JwtValidator implements InitializingBean, Validator<String, String> {

    @Value("${validators.jwt.secret}")
    private String key;

    @Value("${validators.jwt.algorithm}")
    private String signAlgorithm;

    private JwtConsumer jwtConsumer;

    @Override
    public void afterPropertiesSet() throws WalletException {
        jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKey(new HmacKey(key.getBytes()))
                .build();
    }


    public Map<String, Object> getClaims(String token) throws WalletException {
        try {
            JwtClaims claims = jwtConsumer.processToClaims(token);
            return claims.getClaimsMap();
        } catch (InvalidJwtException e) {
            WalletException walletException = new WalletException(e);
            walletException.setUserMessage("Request processing failed");
            throw walletException;
        }
    }

    public String getJson(String token) throws WalletException {
        try {
            return jwtConsumer.processToClaims(token).toJson();
        } catch (InvalidJwtException e) {
            WalletException walletException = new WalletException(e);
            walletException.setUserMessage("Request processing failed");
            throw walletException;        }
    }

    public String createJwt(String payload) throws WalletException {
        JsonWebSignature jws = new JsonWebSignature();
        jws.setKey(new HmacKey(key.getBytes()));
        jws.setAlgorithmHeaderValue(signAlgorithm);
        jws.setPayload(payload);
        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            WalletException walletException = new WalletException(e);
            walletException.setUserMessage("Request processing failed");
            throw walletException;        }
    }


    @Override
    public void validate(@NotNull Map<String, String> inputMap, @NotNull List<String> requiredParameters) throws WalletException {
        throw new WalletException("Unsupported Operation");
    }

    @Override
    public void validate(@NotNull String input, @NotNull List<String> requiredParameters) throws WalletException {
        getClaims(input);
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSignAlgorithm() {
        return signAlgorithm;
    }

    public void setSignAlgorithm(String signAlgorithm) {
        this.signAlgorithm = signAlgorithm;
    }

    public static void main(String... args) throws WalletException {
        JwtValidator validator = new JwtValidator();
        validator.setKey("32#&%+sntc3#%zqlat*qky7_2&ol=lrc34r9o4g=%jj=b1#%!@");
        validator.setSignAlgorithm("HS256");
        String token = validator.createJwt("{\"id\":\"NGN\"}");
        System.out.println(token);
    }
}
