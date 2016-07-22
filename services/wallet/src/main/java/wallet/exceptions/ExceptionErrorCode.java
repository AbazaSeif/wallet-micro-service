package wallet.exceptions;

/**
 * Created by edgardneto on 6/27/16.
 */
public enum ExceptionErrorCode {

    // General errors
    None(1000000),
    ConfigurationError(1000100),

    // Technical errors
    TechnicalError(2000000),
    DatabaseError(2000001),
    RemoteError(2000002),
    TryLater(2000003),
    MalformedResponse(2000004),
    MalformedJSON(2000005),

    // Invalid input errors
    InvalidInput(3000000),
    FieldNull(3000001),
    FieldNotNull(3000002),
    FieldEmpty(3000003),
    FieldNotEmpty(3000004),
    FieldNotInRange(3000005),
    FieldInvalid(3000006),
    FieldTooLow(3000007),
    FieldTooHigh(3000008),
    FieldNotUnique(3000009),
    FieldZero(3000010),
    FieldInList(3000011),
    FieldNotInList(3000012),
    FieldTooShort(3000013),
    FieldTooLong(3000014),
    EntityNotFound(3000100),
    FieldsInvalid(3000101),
    JwtCreation(3000102),

   //Verification errors
    VerificationProcessExpired(5000004),
    VerificationProcessAlreadySuccessfull(5000005),
    VerificationProcessInvalid(5000006),
    UserHasNoEmail(5000007),
    UserHasNoPhoneNumber(5000008),
    UserIsNotOwner(5000009),
    AlreadyVerified(5000010),
    InvalidStatus(5000011),
    VerificationNeeded(5000012),
    FieldIsRequired(5000013),
    MissingRequiredOrVerifiedFieldsForLevel(5000014),
    UnverifiedPhoneNotFound(5000015),
    UnverifiedEmailNotFound(5000015),
    DateOfBirthTooYoung(5000016),
    DateOfBirthTooOld(5000017),

    AddressAlreadyExists(50000016),
    SameValueUpdate(50000017),
    AlreadyVerifiedByAnotherUser(50000018),
    PhoneAlreadyExists(50000019),
    EmailAlreadyExists(50000020),
    EmailLimitReached(50000021),
    MaximumVerificationAttemptsExceeded(50000022),
    EmailHostBlackListed(50000023),
    FileContentTypeNotAllowed(50000024),

    VerificationAttemptLimitReached(50000025),
    VerificationAttemptFailed(50000026),
    WalletTransactionTypeIdAlreadyExists(50000027),


    // Wallet service errors
    ProcessForTokenExpired(7000000),
    InvalidEmailAdderssForToken(7000001),
    InvalidProcessToken(7000002),
    LimitsViolated(7000003),
    FeesHaveChanged(7000004),
    AmbiguousResolve(7000005),
    DuplicatePaymentInstrument(7000006),    // An entered payment instrument already exists in another wallet
    NoPaymentInstrumentSelected(7000007),
    WalletIsNotOwner(7000008),// No payment instrument was selected where one is required
    DuplicateLoyaltyProgramMembership(7000009)


    ;

    private int errorNumber;

    ExceptionErrorCode(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public static ExceptionErrorCode fromErrorNumber(int errorNumber) {
        for (final ExceptionErrorCode errorCode : values()) {
            if (errorCode.errorNumber == errorNumber) {
                return errorCode;
            }
        }
        return null;
    }
}
