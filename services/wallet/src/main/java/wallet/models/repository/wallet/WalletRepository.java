package wallet.models.repository.wallet;

import wallet.exceptions.WalletException;
import wallet.models.entities.Wallet;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;

/**
 * Created by edgardneto on 6/22/16.
 */
@Transactional(rollbackOn = WalletException.class)
public interface WalletRepository extends CrudRepository<Wallet, BigInteger> {


}
