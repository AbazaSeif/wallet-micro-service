package wallet.validators.input;

import org.springframework.stereotype.Component;
import wallet.exceptions.ExceptionErrorCode;
import wallet.exceptions.ExceptionErrorMessage;
import wallet.exceptions.UserErrorMessage;
import wallet.exceptions.WalletException;
import wallet.validators.AbstractValidator;
import wallet.validators.Validator;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 7/12/16.
 */
@Component
public class InputValidator extends AbstractValidator<String, Object> {

    @Override
    public void validate(@NotNull Map<String, Object> inputMap, @NotNull List<String> requiredParameters) throws WalletException {
        for (String parameter : requiredParameters) {
            if (!inputMap.containsKey(parameter) || (inputMap.get(parameter) == null)) {
                String errorMessage = ExceptionErrorMessage.MALFORMED_JSON + ExceptionErrorMessage.MISSING_REQUIRED_FIELD + parameter;
                String errorUserMessage = UserErrorMessage.REQUEST_PROCESSING_FAILED;
                WalletException walletException = new WalletException(errorMessage, ExceptionErrorCode.MissingRequiredOrVerifiedFieldsForLevel);
                walletException.setUserMessage(errorUserMessage);
                throw walletException;
            }
        }
    }

}
