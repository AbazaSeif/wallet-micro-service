package wallet.controllers.responses;

import wallet.exceptions.WalletException;
import wallet.models.entities.Currency;
import wallet.models.entities.Wallet;
import wallet.models.entities.WalletTransaction;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by edgardneto on 7/11/16.
 */
public class ResponseBuilder {


    private static final Map<String, Response> factoryMap = Collections.unmodifiableMap(
            new ConcurrentHashMap<String, Response>() {
                {
                    put(Object.class.getName(), new WalletResponse<>());
                    put(Wallet.class.getName(), new WalletResponse<Wallet>());
                    put(WalletTransaction.class.getName(), new WalletResponse<WalletTransaction>());
                    put(Currency.class.getName(), new WalletResponse<Currency>());
                    put(String.class.getName(), new WalletResponse<String>());
                }
            });

    public static <T> Response<T> build(List<T> message, String status) throws WalletException {
        String className = message.isEmpty() ? Object.class.getName() : message.get(0).getClass().getName();
        Response<T> myResponse = factoryMap.get(className);
        myResponse.setMessages(message);
        myResponse.setStatus(status);
        return myResponse;
    }

    public static <T> Response<T> build( T message, String status) throws WalletException {
        String className = (message == null) ? Object.class.getName() : message.getClass().getName();
        Response<T> myResponse = factoryMap.get(className);
        List<T> messageList = Collections.singletonList(message);
        myResponse.setMessages(messageList);
        myResponse.setStatus(status);
        return myResponse;
    }

}
