package wallet.models.repository.wallets.transactions;

import wallet.exceptions.WalletException;
import wallet.models.entities.WalletTransaction;

import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 19/07/2016.
 */
public interface WalletTransactionRepositoryCustom {

    List<WalletTransaction> findAllWalletTransactionByMapCriteria(Map<String, Object> criteriaMap) throws WalletException;
}
