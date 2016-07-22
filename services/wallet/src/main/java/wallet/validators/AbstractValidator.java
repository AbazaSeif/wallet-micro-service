package wallet.validators;

import wallet.exceptions.ExceptionErrorCode;
import wallet.exceptions.ExceptionErrorMessage;
import wallet.exceptions.WalletException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 7/22/16.
 */
public abstract class AbstractValidator<k, v> implements Validator<k, v> {


    @Override
    public void validate(@NotNull Map<k, v> inputMap, @NotNull List<k> requiredParameters) throws WalletException {
        throw new WalletException(ExceptionErrorMessage.UNSUPPORTED_OPERATION);
    }

    @Override
    public void validate(@NotNull k input, @NotNull List<k> requiredParameters) throws WalletException {
        throw new WalletException(ExceptionErrorMessage.UNSUPPORTED_OPERATION);
    }

    @Override
    public void checkThat(boolean condition, ExceptionErrorCode exceptionErrorCode, String exceptionErrorMessage, String userErrorMessage) throws WalletException {
        if (!condition) {
            throw new WalletException(exceptionErrorMessage, userErrorMessage, exceptionErrorCode, null);
        }
    }
}
