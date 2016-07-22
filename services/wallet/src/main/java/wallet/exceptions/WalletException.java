package wallet.exceptions;

/**
 * Created by edgardneto on 25-06-2016.
 */
public class WalletException extends Exception  {

    private static final long serialVersionUID = 1997753363232807009L;

    private ErrorCode errorCode;

    private String userMessage;

    public WalletException() {
    }

    public WalletException(String message) {
        super(message);
    }


    public WalletException(Throwable cause) {
        super(cause);
    }

    public WalletException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public WalletException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public WalletException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
    }

    public WalletException(String message,String userMessage, ErrorCode errorCode, Throwable cause) {
        this(message,errorCode ,cause);
        this.userMessage=userMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

}
