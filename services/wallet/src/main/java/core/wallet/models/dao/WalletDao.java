package core.wallet.models.dao;

import core.wallet.models.entities.Currency;
import core.wallet.models.entities.Wallet;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by edgardneto on 6/22/16.
 */
@Transactional(rollbackOn = Exception.class)
public interface WalletDao extends CrudRepository<Wallet, BigInteger> {


        List<Wallet> findAllByfkCountry(String fk_country) throws Exception;

        List<Wallet> findAllByfkCurrency(Currency fk_currency) throws Exception;

       // List<Wallet> findAllByFkCountryAndFkCurrency(String fk_country, Currency fk_currency) throws Exception;



}
