package wallet.controllers.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import wallet.exceptions.WalletException;

import java.util.List;

/**
 * Created by edgardneto on 03-07-2016.
 */
public class WalletResponse<T> implements Response<T> {

    private List<T> message;
    private String status;


    public WalletResponse() {

    }

    public WalletResponse(List<T> message, String status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public List<T> getMessages() throws WalletException {
        return message;
    }

    @Override
    public void setMessages(List<T> message) throws WalletException {
        this.message = message;
    }


    @Override
    public String getStatus() throws WalletException {
        return status;
    }

    @Override
    public void setStatus(String status) throws WalletException {
        this.status = status;
    }

    @Override
    @JsonIgnore
    public List<String> getErrors() throws WalletException {
        throw new WalletException(new UnsupportedOperationException());
    }

    @Override
    @JsonIgnore
    public void setErrors(List<String> error) throws WalletException {
        throw new WalletException(new UnsupportedOperationException());
    }

}
