package wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wallet.exceptions.ExceptionErrorCode;
import wallet.exceptions.ExceptionErrorMessage;
import wallet.exceptions.UserErrorMessage;
import wallet.exceptions.WalletException;
import wallet.models.entities.Wallet;
import wallet.models.entities.WalletTransaction;
import wallet.models.entities.WalletTransactionType;
import wallet.models.repository.wallet.transactions.WalletTransactionRepository;
import wallet.models.repository.wallet.transactions.types.WalletTransactionTypeRepository;
import wallet.validators.input.InputValidator;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 7/20/16.
 */

@Service
public class WalletTransactionService {
    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Autowired
    private WalletTransactionTypeRepository walletTransactionTypeRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private InputValidator inputValidator;

    @Transactional(rollbackOn = WalletException.class)
    public WalletTransaction createWalletTransaction(String wallet_debit_id, String amount, String wallet_credit_id, String transactionReference, WalletTransactionType walletTransactionType, String description, String additionalInfo) throws WalletException {

        //Find and validate both credit and debit Wallet
        Wallet debitWallet = walletService.getWallet(wallet_debit_id);
        inputValidator.checkThat(debitWallet != null, ExceptionErrorCode.FieldNull, ExceptionErrorMessage.WALLET_OBJECT_IS_NULL, UserErrorMessage.REQUEST_PROCESSING_FAILED);

        Wallet creditWallet = walletService.getWallet(wallet_credit_id);
        inputValidator.checkThat(debitWallet != null, ExceptionErrorCode.FieldNull, ExceptionErrorMessage.WALLET_OBJECT_IS_NULL, UserErrorMessage.REQUEST_PROCESSING_FAILED);

        //Update and Commit Wallets Balance
        walletService.updateWalletBalance(debitWallet, creditWallet, amount);

        //Create Transaction
        BigDecimal transactionAmount = new BigDecimal(amount);
        WalletTransaction walletTransaction = new WalletTransaction(debitWallet, creditWallet, transactionAmount, transactionReference, walletTransactionType);
        walletTransaction.setDescription(description);
        walletTransaction.setAdditionalInformation(additionalInfo);

        //Commit Transaction
        walletTransaction = walletTransactionRepository.save(walletTransaction);

        //return wallet transaction
        return walletTransaction;
    }

    @Transactional(rollbackOn = WalletException.class)
    public List<WalletTransactionType> getAllWalletTransactionTypes() throws WalletException {
        return (List<WalletTransactionType>) walletTransactionTypeRepository.findAll();
    }

    @Transactional(rollbackOn = WalletException.class)
    public WalletTransactionType getWalletTransactionType(String id) throws WalletException {
        return walletTransactionTypeRepository.findOne(id);
    }

    @Transactional(rollbackOn = WalletException.class)
    public WalletTransactionType createWalletTransactionType(String id) throws WalletException {
        //Create and Store new walletTransactionType
        boolean exists = walletTransactionTypeRepository.exists(id);
        inputValidator.checkThat(!exists, ExceptionErrorCode.WalletTransactionTypeIdAlreadyExists, ExceptionErrorMessage.ID_IS_NOT_UNIQUE, UserErrorMessage.REQUEST_PROCESSING_FAILED);
        return walletTransactionTypeRepository.save(new WalletTransactionType(id));
    }

    @Transactional(rollbackOn = WalletException.class)
    public WalletTransaction getWalletTransaction(BigInteger id) throws WalletException {
        return walletTransactionRepository.findOne(id);
    }

    @Transactional(rollbackOn = WalletException.class)
    public List<WalletTransaction> getWalletTransactionByCriteriaMap(Map<String, Object> criteriaMap) throws WalletException {
        return walletTransactionRepository.findAllWalletTransactionByMapCriteria(criteriaMap);

    }
}
