package core.wallet.models.dao;

import core.wallet.models.entities.WalletTransaction;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

/**
 * Created by edgardneto on 23-06-2016.
 */
public interface WalletTransactionDao extends CrudRepository<WalletTransaction,BigInteger> {





}
