package wallet.models.repository.wallet.transactions;

import wallet.exceptions.WalletException;
import wallet.models.entities.WalletTransaction;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;

/**
 * Created by edgardneto on 23-06-2016.
 */

@Transactional(rollbackOn = WalletException.class)
public interface WalletTransactionRepository extends CrudRepository<WalletTransaction, BigInteger>,WalletTransactionRepositoryCustom {

}
