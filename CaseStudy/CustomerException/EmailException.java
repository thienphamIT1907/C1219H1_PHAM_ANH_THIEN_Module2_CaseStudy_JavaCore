package CaseStudy.CustomerException;

public class EmailException extends Exception {
    public EmailException(String notification) {
        super(notification);
    }
}
