package wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wallet.controllers.mapping.urls.RequestMappingUrl;
import wallet.controllers.responses.Response;
import wallet.controllers.responses.ResponseBuilder;
import wallet.exceptions.WalletException;
import wallet.models.entities.Wallet;
import wallet.services.WalletService;
import wallet.services.WalletTransactionService;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by edgardneto on 6/22/16.
 */

@RestController
@RequestMapping(RequestMappingUrl.WALLET_MAPPING_URL)
public class WalletController extends AbstractBaseController<String> {

    @Autowired
    WalletService walletService;

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
        Map<String, Object> requestBodyMap = jwtValidator.getClaims(token);

        //Validate Input
        inputValidator.validate(requestBodyMap, Arrays.asList("currencyId", "countryId"));

        //Find WalletTransactionType
        String fkCurrency = (String) requestBodyMap.get("currencyId");
        String fkCountry = (String) requestBodyMap.get("countryId");

        //Create Wallet
        Wallet wallet = walletService.createWallet(fkCurrency, fkCountry);

        //Create Jwt
        String jwt = jwtValidator.createJwt(wallet.toString());
        return ResponseBuilder.build(jwt, HttpStatus.OK.name());
    }


    @Override
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> getById(@PathVariable String id) throws WalletException {
        //Find Wallet
        Wallet wallet = walletService.getWallet(id);

        //Create Jwt
        String jwt = jwtValidator.createJwt(wallet.toString());
        return ResponseBuilder.build(jwt, HttpStatus.OK.name());
    }

}
