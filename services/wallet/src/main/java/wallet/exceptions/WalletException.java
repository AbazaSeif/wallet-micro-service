package wallet.exceptions;

/**
 * Created by edgardneto on 25-06-2016.
 */
public class WalletException extends Exception  {

    private static final long serialVersionUID = 1997753363232807009L;

    private ExceptionErrorCode errorCode;

    private String userMessage;

    public WalletException() {
    }

    public WalletException(String message) {
        super(message);
    }


    public WalletException(Throwable cause) {
        super(cause);
    }

    public WalletException(String message, ExceptionErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public WalletException(ExceptionErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public WalletException(String message, ExceptionErrorCode errorCode, Throwable cause) {
        super(message, cause);
    }

    public WalletException(String message, String userMessage, ExceptionErrorCode errorCode, Throwable cause) {
        this(message,errorCode ,cause);
        this.userMessage=userMessage;
    }

    public ExceptionErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ExceptionErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

}
