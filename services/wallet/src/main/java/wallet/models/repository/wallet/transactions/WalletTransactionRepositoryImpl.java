package wallet.models.repository.wallet.transactions;

import wallet.exceptions.WalletException;
import wallet.models.entities.WalletTransaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 19/07/2016.
 */
public class WalletTransactionRepositoryImpl implements WalletTransactionRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WalletTransaction> findAllWalletTransactionByMapCriteria(Map<String, Object> criteriaMap) throws WalletException {
        EntityManager localEm = em.getEntityManagerFactory().createEntityManager();

        String prefixQuery = "SELECT * FROM wallet_transaction ";
        String queryCriteria = "WHERE ";
        for (Map.Entry<String, Object> entries : criteriaMap.entrySet()) {
            if (!String.valueOf(entries.getValue()).isEmpty())
                queryCriteria = queryCriteria.concat(entries.getKey()).concat("='").concat(String.valueOf(entries.getValue())).concat("' AND ");
        }
        queryCriteria = (queryCriteria.length() > 6) ? queryCriteria.substring(0, queryCriteria.lastIndexOf(" AND ")).concat(";") : ";";
        String query = prefixQuery.concat(queryCriteria);
        Query nativeQuery = localEm.createNativeQuery(query, WalletTransaction.class);
        List<WalletTransaction> walletTransactionList = nativeQuery.getResultList();
        localEm.clear();
        localEm.close();
        return walletTransactionList;
    }
}
