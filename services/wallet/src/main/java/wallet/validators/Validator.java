package wallet.validators;

import wallet.exceptions.WalletException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 7/12/16.
 */
public interface Validator<k, V> {


    void validate(@NotNull Map<k, V> inputMap, @NotNull List<k> requiredParameters) throws WalletException;
    void validate(@NotNull k input, @NotNull List<k> requiredParameters) throws WalletException;


}
