package wallet.controllers;

import wallet.controllers.mapping.urls.RequestMappingUrl;
import wallet.controllers.responses.Response;
import wallet.controllers.responses.ResponseBuilder;
import wallet.exceptions.WalletException;
import wallet.models.entities.WalletTransactionType;
import wallet.services.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 6/23/16.
 */
@RestController
@RequestMapping(RequestMappingUrl.WALLET_TRANSACTION_TYPE_MAPPING_URL)
public class WalletTransactionTypeController extends AbstractBaseController<String> {

    @Autowired
    WalletTransactionService walletTransactionService;


    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> create(
            @RequestBody String token
    ) throws WalletException {
        //Validate Jwt
        Map<String, Object> requestBody = jwtValidator.getClaims(token);

        //Validate Input
        inputValidator.validate(requestBody, Collections.singletonList("id"));

        //Create and Store new walletTransactionType
        String walletTransactionTypeId = (String) requestBody.get("id");
        WalletTransactionType walletTransactionType = walletTransactionService.createWalletTransactionType(walletTransactionTypeId);

        //Create Jwt
        String jwt = jwtValidator.createJwt(walletTransactionType.toString());

        //Create and Send Response
        return ResponseBuilder.build(jwt, HttpStatus.OK.name());
    }


    @Override
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> getById(
            @PathVariable String id
    ) throws WalletException {
        //Find WalletTransactionType
        WalletTransactionType walletTransactionType = walletTransactionService.getWalletTransactionType(id);

        //Create Jwt
        String jwt = jwtValidator.createJwt(walletTransactionType.toString());

        return ResponseBuilder.build(jwt, HttpStatus.OK.name());
    }

    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> getAll() throws WalletException {
        //Find Currency
        List<WalletTransactionType> currencyList = walletTransactionService.getAllWalletTransactionTypes();

        //Create Jwt
        String jwt = jwtValidator.createJwt(currencyList.toString());

        //Build and return response
        return ResponseBuilder.build(jwt, HttpStatus.OK.name());
    }

}
