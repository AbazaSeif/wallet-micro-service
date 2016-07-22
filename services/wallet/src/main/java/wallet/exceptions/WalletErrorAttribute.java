package wallet.exceptions;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * Created by edgardneto on 7/13/16.
 */
@Component
public class WalletErrorAttribute extends DefaultErrorAttributes {

    private final String MESSAGE_KEY = "message";
    private final String THROWABLE_KEY = "throwable";

    @Override
    public Map<String, Object> getErrorAttributes(
            RequestAttributes requestAttributes,
            boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        Throwable error = getError(requestAttributes);
        if (error instanceof WalletException) {
            errorAttributes.put(MESSAGE_KEY, ((WalletException) error).getUserMessage());
            errorAttributes.put(THROWABLE_KEY, error.getMessage());
        }
        return errorAttributes;
    }
}
