package wallet.exceptions;

/**
 * Created by edgardneto on 7/22/16.
 */
public interface ExceptionErrorMessage {

    String MALFORMED_JSON = "Malformed JSON. ";
    String MISSING_REQUIRED_FIELD = "Missing required field: ";
    String UNSUPPORTED_OPERATION = "Unsupported Operation. ";
    String WALLET_OBJECT_IS_NULL = "Wallet object is NULL. ";
    String CURRENCY_OBJECT_IS_NULL = "Currency object is NULL. ";
    String ID_IS_NOT_UNIQUE = "The ID is already exists in database. ";
    String INSUFFICIENT_FUND = "Transaction amount exceeds the debit wallet amount.";



}
