package core.wallet.controllers;

import core.wallet.models.dao.WalletTransactionTypeDao;
import core.wallet.models.entities.WalletTransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by edgardneto on 6/23/16.
 */
@Controller
public class WalletTransactionTypeController {
    @Autowired
    WalletTransactionTypeDao walletTransactionTypeDao;


    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping(value = "/wallet/transaction/type",
            method = RequestMethod.POST,
            consumes = "application/JSON")
    @ResponseBody
    public void create(@RequestParam String name) throws Exception {
        walletTransactionTypeDao.save(new WalletTransactionType(name));
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping(value = "/wallet/transaction/type/{id}",
            method = RequestMethod.GET,
            produces = "application/JSON")
    @ResponseBody
    public String getById(@PathVariable String id) throws Exception {
        return walletTransactionTypeDao.findOne(id).toString();
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping(value = "/wallet/transaction/type",
            method = RequestMethod.GET,
            produces = "application/JSON")
    @ResponseBody
    public String getAll() throws Exception {
        return walletTransactionTypeDao.findAll().toString();
    }


    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping(value = "/wallet/transaction/type/{id}",
            method = RequestMethod.DELETE,
            consumes = "application/JSON")
    @ResponseBody
    public void deleteById(@PathVariable String id) throws Exception {
        walletTransactionTypeDao.delete(id);
    }



}
