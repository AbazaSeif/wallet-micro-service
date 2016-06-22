package core.wallet.models.dao;

import core.wallet.models.entities.Wallet;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;

/**
 * Created by edgardneto on 6/22/16.
 */
@Transactional(rollbackOn = Exception.class)
public interface WalletDao extends CrudRepository<Wallet,BigInteger>{






}
