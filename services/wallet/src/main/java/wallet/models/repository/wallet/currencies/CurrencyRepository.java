package wallet.models.repository.wallet.currencies;

import wallet.exceptions.WalletException;
import wallet.models.entities.Currency;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by edgardneto on 6/22/16.
 */
@Transactional(rollbackOn = WalletException.class)
public interface CurrencyRepository extends CrudRepository<Currency, String> {

}
