package wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wallet.exceptions.WalletException;
import wallet.models.entities.Currency;
import wallet.models.entities.Wallet;
import wallet.models.repository.wallets.WalletRepository;
import wallet.models.repository.wallets.currencies.CurrencyRepository;

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


    public Wallet getWallet(String id) throws WalletException {
        return walletRepository.findOne(new BigInteger(id));
    }

    @Transactional(rollbackOn = WalletException.class)
    public Wallet createWallet(String fkCurrency, String fkCountry) throws WalletException {
        BigDecimal balance = new BigDecimal("0.0");
        Currency currency = currencyRepository.findOne(fkCurrency);
        Wallet wallet = new Wallet(balance, fkCountry, currency);
        wallet = walletRepository.save(wallet);
        return wallet;
    }

    @Transactional(rollbackOn = WalletException.class)
    public Wallet updateWallet(Wallet wallet, String amount, boolean isDebit) throws WalletException {

        //Create Transaction Amount to debit or to credit
        BigDecimal transactionAmount = isDebit ? new BigDecimal(amount).negate() : new BigDecimal(amount);

        //Update Source Wallet Balance
        BigDecimal currentWalletBalance = wallet.getBalance();
        wallet.setBalance(currentWalletBalance.add(transactionAmount));

        //Commit Wallets Updates
        wallet = walletRepository.save(wallet);

        //return updated wallets
        return wallet;
    }

    @Transactional(rollbackOn = WalletException.class)
    public List<Wallet> updateWallet(Wallet debtWallet, Wallet creditWallet, String amount) throws WalletException {

        //Create Transaction Amount to debit or to credit
        BigDecimal transactionAmount = new BigDecimal(amount);

        //Get Debit Wallet Balance
        BigDecimal debtWalletBalance = debtWallet.getBalance();

        //Get Credit Wallet Balance
        BigDecimal creditWalletBalance = creditWallet.getBalance();

        //Update credit and debit Wallet Balances
        debtWallet.setBalance(debtWalletBalance.subtract(transactionAmount));
        creditWallet.setBalance(creditWalletBalance.add(transactionAmount));

        //return updated wallets
        return (List<Wallet>) walletRepository.save(Arrays.asList(debtWallet, creditWallet));
    }


}
