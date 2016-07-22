package wallet.validators.input;

import wallet.exceptions.ErrorCode;
import wallet.exceptions.WalletException;
import wallet.validators.Validator;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 7/12/16.
 */
@Component
public class InputValidator implements Validator<String, Object> {


    @Override
    public void validate(@NotNull Map<String, Object> inputMap, @NotNull List<String> requiredParameters) throws WalletException {
        for (String parameter : requiredParameters) {
            if (!inputMap.containsKey(parameter) || (inputMap.get(parameter) == null)) {
                WalletException walletException = new WalletException("Malformed JSON. Missing required field: " + parameter, ErrorCode.MissingRequiredOrVerifiedFieldsForLevel);
                walletException.setUserMessage("Request processing failed");
                throw walletException;
            }
        }
    }

    @Override
    public void validate(@NotNull String input, @NotNull List<String> requiredParameters) throws WalletException {
        throw new WalletException("Unsupported Operation");
    }

}
