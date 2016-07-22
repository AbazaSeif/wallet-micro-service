package wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wallet.exceptions.ExceptionErrorCode;
import wallet.exceptions.ExceptionErrorMessage;
import wallet.exceptions.UserErrorMessage;
import wallet.exceptions.WalletException;
import wallet.models.entities.Currency;
import wallet.models.entities.Wallet;
import wallet.models.repository.wallet.WalletRepository;
import wallet.models.repository.wallet.currencies.CurrencyRepository;
import wallet.validators.input.InputValidator;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * Created by edgardneto on 19/07/2016.
 */

@Service
public class WalletService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private InputValidator inputValidator;

    public Wallet getWallet(String id) throws WalletException {
        return walletRepository.findOne(new BigInteger(id));
    }

    @Transactional(rollbackOn = WalletException.class)
    public Wallet createWallet(String fkCurrency, String fkCountry) throws WalletException {
        //Create Initial Amount
        BigDecimal balance = new BigDecimal("0.0");

        //Find Currency
        Currency currency = currencyRepository.findOne(fkCurrency);
        inputValidator.checkThat(currency!=null, ExceptionErrorCode.FieldNull, ExceptionErrorMessage.CURRENCY_OBJECT_IS_NULL, UserErrorMessage.REQUEST_PROCESSING_FAILED);

        Wallet wallet = new Wallet(balance, fkCountry, currency);
        wallet = walletRepository.save(wallet);
        return wallet;
    }

    @Transactional(rollbackOn = WalletException.class)
    public Wallet updateWalletBalance(Wallet wallet, String amount, boolean isDebit) throws WalletException {

        //Create Transaction Amount to debit or to credit
        BigDecimal transactionAmount = isDebit ? new BigDecimal(amount).negate() : new BigDecimal(amount);

        //Get Wallet Balance
        BigDecimal currentWalletBalance = wallet.getBalance();

        //Check that Debit Wallet has enough funds
        if (isDebit)
            inputValidator.checkThat(currentWalletBalance.compareTo(transactionAmount) > -1, ExceptionErrorCode.LimitsViolated, ExceptionErrorMessage.INSUFFICIENT_FUND, UserErrorMessage.INSUFFICIENT_FUND);

        //Update Wallet Balance
        wallet.setBalance(currentWalletBalance.add(transactionAmount));

        //Commit Wallets Updates
        wallet = walletRepository.save(wallet);

        //return updated wallets
        return wallet;
    }

    @Transactional(rollbackOn = WalletException.class)
    public List<Wallet> updateWalletBalance(Wallet debtWallet, Wallet creditWallet, String amount) throws WalletException {

        //Create Transaction Amount to debit or to credit
        BigDecimal transactionAmount = new BigDecimal(amount);

        //Get Debit Wallet Balance
        BigDecimal debtWalletBalance = debtWallet.getBalance();

        //Check that Debit Wallet has enough funds
        inputValidator.checkThat(debtWalletBalance.compareTo(transactionAmount) > -1, ExceptionErrorCode.LimitsViolated, ExceptionErrorMessage.INSUFFICIENT_FUND, UserErrorMessage.INSUFFICIENT_FUND);

        //Get Credit Wallet Balance
        BigDecimal creditWalletBalance = creditWallet.getBalance();

        //Update credit and debit Wallet Balances
        debtWallet.setBalance(debtWalletBalance.subtract(transactionAmount));
        creditWallet.setBalance(creditWalletBalance.add(transactionAmount));

        //return updated wallets
        return (List<Wallet>) walletRepository.save(Arrays.asList(debtWallet, creditWallet));
    }


}
