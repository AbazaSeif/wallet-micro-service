package wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wallet.controllers.mapping.urls.RequestMappingUrl;
import wallet.controllers.responses.Response;
import wallet.controllers.responses.ResponseBuilder;
import wallet.exceptions.WalletException;
import wallet.models.entities.WalletTransaction;
import wallet.models.entities.WalletTransactionType;
import wallet.services.WalletTransactionService;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edgardneto on 25-06-2016.
 */
@RestController
@RequestMapping(RequestMappingUrl.WALLET_MAPPING_URL)
public class WalletTransactionController extends AbstractBaseController<String> {

    @Autowired
    WalletTransactionService walletTransactionService;

    @Override
    @RequestMapping(
            value = RequestMappingUrl.TRANSACTION_MAPPING_URL,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> create(
            @RequestBody String token
    ) throws WalletException {
        //Validate Jwt
        Map<String, Object> requestBodyMap = jwtValidator.getClaims(token);

        //Validate Input
        inputValidator.validate(requestBodyMap, Arrays.asList("wallet_debit", "wallet_credit",
                "transaction_amount", "transaction_reference", "transaction_type", "transaction_description"));

        //Find WalletTransactionType
        String wallet_debit_id = (String) requestBodyMap.get("wallet_debit");
        String wallet_credit_id = (String) requestBodyMap.get("wallet_credit");
        String amount = (String) requestBodyMap.get("transaction_amount");
        String transaction_reference = (String) requestBodyMap.get("transaction_reference");
        String transaction_type = (String) requestBodyMap.get("transaction_type");
        String description = (String) requestBodyMap.get("transaction_description");
        String additional_information = (requestBodyMap.containsKey("transaction_additional_information")) ? (String) requestBodyMap.get("transaction_additional_information") : "";

        //Find Wallet Transaction Type
        WalletTransactionType walletTransactionType = walletTransactionService.getWalletTransactionType(transaction_type);

        //Create and Commit Transaction
        WalletTransaction walletTransaction = walletTransactionService.createWalletTransaction(wallet_debit_id, amount, wallet_credit_id, transaction_reference, walletTransactionType, description, additional_information);

        //Create Jwt and Response
        String jwt = jwtValidator.createJwt(walletTransaction.toString());
        return ResponseBuilder.build(jwt, HttpStatus.OK.name());

    }

    @Override
    @RequestMapping(
            value = RequestMappingUrl.TRANSACTION_MAPPING_URL + "/{transactionId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> getById(
            @PathVariable String transactionId
    ) throws WalletException {
        WalletTransaction walletTransaction = walletTransactionService.getWalletTransaction(new BigInteger(transactionId));

        //Create Jwt
        String jwt = jwtValidator.createJwt(walletTransaction.toString());
        return ResponseBuilder.build(jwt, HttpStatus.OK.name());
    }


    @RequestMapping(
            value = "/{mainWalletId}" + RequestMappingUrl.TRANSACTIONS_MAPPING_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> getWalletTransactions(
            @PathVariable String mainWalletId,
            @RequestParam(required = false, defaultValue = "") String secWalletId,
            @RequestParam(required = false, defaultValue = "") String referenceId,
            @RequestParam(required = false, defaultValue = "") String typeId,
            @RequestParam(required = false, defaultValue = "false") boolean isCredit
    ) throws WalletException {
        //Create Mapping Criteria
        Map<String, Object> criteriaMap = new HashMap<>();
        criteriaMap.put("fk_wallet_source", isCredit ? secWalletId : mainWalletId);
        criteriaMap.put("fk_wallet_dest", isCredit ? mainWalletId : secWalletId);
        criteriaMap.put("reference", referenceId);
        criteriaMap.put("fk_transaction_type", typeId);

        //Get Wallet Transactions applied to criteriaMap
        List<WalletTransaction> walletTransactions = walletTransactionService.getWalletTransactionByCriteriaMap(criteriaMap);

        //Create Jwt
        String jwt = jwtValidator.createJwt(walletTransactions.toString());
        return ResponseBuilder.build(jwt, HttpStatus.OK.name());
    }

}
