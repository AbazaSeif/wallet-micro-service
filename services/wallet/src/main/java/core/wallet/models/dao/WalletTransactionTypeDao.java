package core.wallet.models.dao;

import core.wallet.models.entities.WalletTransactionType;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by edgardneto on 23-06-2016.
 */


@Transactional(rollbackOn = Exception.class)
public interface WalletTransactionTypeDao extends CrudRepository<WalletTransactionType, Integer> {

    List<WalletTransactionType> findByName(String name) throws Exception;


}
