package wallet.controllers.responses;

import wallet.exceptions.WalletException;

import java.util.List;

/**
 * Created by edgardneto on 03-07-2016.
 */
public interface Response<T> {

    List<T> getMessages() throws WalletException;


    void setMessages(List<T> message) throws WalletException;


    String getStatus() throws WalletException;

    void setStatus(String status) throws WalletException;

    List<String> getErrors() throws WalletException;

    void setErrors(List<String> error) throws WalletException;



}
