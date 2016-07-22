package wallet.validators;

import org.springframework.core.ErrorCoded;
import wallet.exceptions.ExceptionErrorCode;
import wallet.exceptions.ExceptionErrorMessage;
import wallet.exceptions.UserErrorMessage;
import wallet.exceptions.WalletException;
import wallet.models.entities.Wallet;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 7/12/16.
 */
public interface Validator<k, V> {


    void validate(@NotNull Map<k, V> inputMap, @NotNull List<k> requiredParameters) throws WalletException;
    void validate(@NotNull k input, @NotNull List<k> requiredParameters) throws WalletException;
    void checkThat(boolean condition, ExceptionErrorCode exceptionErrorCode, String exceptionErrorMessage, String userErrorMessage) throws WalletException;

}
