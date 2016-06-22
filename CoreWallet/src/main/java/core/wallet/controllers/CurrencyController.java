package core.wallet.controllers;

import core.wallet.models.dao.CurrencyDao;
import core.wallet.models.entities.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by edgardneto on 6/22/16.
 */
@Controller
public class CurrencyController {


    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping(value = "/currency",
            method = RequestMethod.POST,
            consumes = "application/JSON")
    @ResponseBody
    public void create(@RequestParam String code) throws Exception {
        currencyDao.save(new Currency(code));
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping(value = "/currency/{code}", method = RequestMethod.GET,
            produces = "application/JSON")
    @ResponseBody
    public String getById(@PathVariable String code) throws Exception {
        return currencyDao.findById(code).toString();
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping(value = "/currency/", method = RequestMethod.GET,
            produces = "application/JSON")
    @ResponseBody
    public String getAll() throws Exception {
        return currencyDao.findAll().toString();
    }


    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping(value = "/currency/{code}",
            method = RequestMethod.DELETE,
            consumes = "application/JSON")
    @ResponseBody
    public void deleteById(@PathVariable String code) throws Exception {
        currencyDao.delete(code);
    }

    @Autowired
    private CurrencyDao currencyDao;


}
