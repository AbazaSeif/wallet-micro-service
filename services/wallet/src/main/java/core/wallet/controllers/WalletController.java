package core.wallet.controllers;

import core.wallet.models.dao.CurrencyDao;
import core.wallet.models.dao.WalletDao;
import core.wallet.models.entities.Currency;
import core.wallet.models.entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by edgardneto on 6/22/16.
 */

@Controller
public class WalletController {


    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping(value = "/wallet",
            method = RequestMethod.POST,
            consumes = "application/JSON")
    @ResponseBody
    public void createWallet(
            @RequestParam String fkCountry,
            @RequestParam String fkCurrency,
            @RequestParam(defaultValue = "0.0") String initialBalance,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        BigDecimal balance = new BigDecimal(initialBalance);
        Currency currency = currencyDao.findById(fkCurrency);
        Wallet wallet = new Wallet(balance, fkCountry, currency);
        walletDao.save(wallet);
    }

    @RequestMapping(value = "/wallet/{walletId}",
            method = RequestMethod.GET,
            produces = "application/JSON")
    @ResponseBody
    public String getWallet(
            @PathVariable String walletId,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        Wallet wallet = walletDao.findOne(new BigInteger(walletId));
        return wallet.toString();
    }

    @RequestMapping(value = "/wallet/{walletId}",
            method = RequestMethod.POST,
            produces = "application/JSON",
            consumes = "application/JSON"
    )
    @ResponseBody
    public void updateWallet(
            @PathVariable String walletId,
            @RequestParam(required = false) String balance,
            @RequestParam(required = false) String fkCountry,
            @RequestParam(required = false) String fkCurrency,
            HttpServletRequest httpServletRequest
    ) throws Exception {
        boolean updateBalance = balance != null && StringUtils.hasText(balance);
        boolean updateFkCountry = fkCountry != null && StringUtils.hasText(fkCountry) && fkCountry.length() == 2;
        boolean updateFkCurrency = fkCurrency != null && StringUtils.hasText(fkCurrency) && fkCurrency.length() == 3;

        Wallet wallet = walletDao.findOne(new BigInteger(walletId));

        if (updateBalance) {
            wallet.setBalance(new BigDecimal(balance));
        }
        if (updateFkCountry) {
            wallet.setFkCountry(fkCountry);
        }
        if (updateFkCurrency) {
            Currency currency = currencyDao.findOne(fkCurrency);
            wallet.setFkCurrency(currency);
        }
        if(updateBalance || updateFkCountry || updateFkCurrency){
            walletDao.save(wallet);
        }

    }


    @Autowired
    CurrencyDao currencyDao;

    @Autowired
    WalletDao walletDao;

}
