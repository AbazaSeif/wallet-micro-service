package wallet.controllers;

import wallet.controllers.responses.Response;
import wallet.exceptions.WalletException;

/**
 * Created by edgardneto on 7/14/16.
 */
public interface BaseController<T> {

    Response<T> create(String token) throws WalletException;
    Response<T> update(String token) throws WalletException;
    Response<T> getById(String token) throws WalletException;
    Response<T> getAll() throws WalletException;



}
