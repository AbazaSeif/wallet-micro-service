package wallet.controllers;

import wallet.controllers.responses.Response;
import wallet.exceptions.WalletException;
import wallet.validators.input.InputValidator;
import wallet.validators.jwt.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by edgardneto on 7/14/16.
 */
@Component
public abstract class AbstractBaseController<T> implements BaseController<T>{

    @Autowired
    protected JwtValidator jwtValidator;

    @Autowired
    protected InputValidator inputValidator;

    @Override
    public Response<T> create(String token) throws WalletException {
        return null;
    }

    @Override
    public Response<T> update(String token) throws WalletException {
        return null;
    }

    @Override
    public Response<T> getById(String token) throws WalletException {
        return null;
    }

    @Override
    public Response<T> getAll() throws WalletException {
        return null;
    }
}
