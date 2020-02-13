package gmail.roadtojob2019.brewery.exception;

public class SuchCustomerAlreadyExistException extends Exception {

    public SuchCustomerAlreadyExistException() {
        super();
    }

    public SuchCustomerAlreadyExistException(final String message) {
        super(message);
    }
}
