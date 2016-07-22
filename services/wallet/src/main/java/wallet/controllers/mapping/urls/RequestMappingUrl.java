package wallet.controllers.mapping.urls;

/**
 * Created by edgardneto on 25-06-2016.
 */
public interface RequestMappingUrl {

    String WALLET_MAPPING_URL = ("/wallet");
    String TRANSACTION_MAPPING_URL = ("/transaction");
    String TRANSACTIONS_MAPPING_URL = ("/transactions");
    String WALLET_TRANSACTION_MAPPING_URL = (WALLET_MAPPING_URL + "/transaction");
    String WALLET_TRANSACTION_TYPE_MAPPING_URL = (WALLET_TRANSACTION_MAPPING_URL + "/type");


}
