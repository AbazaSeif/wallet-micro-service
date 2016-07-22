package wallet.models.repository.wallets.transactions.types;

import wallet.models.entities.WalletTransactionType;
import wallet.exceptions.WalletException;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by edgardneto on 23-06-2016.
 */


@Transactional(rollbackOn = WalletException.class)
public interface WalletTransactionTypeRepository extends CrudRepository<WalletTransactionType, String> {

}
