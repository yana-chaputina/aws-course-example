package aws.example.exception;

public class FileException extends RuntimeException {
    public FileException(String message) {
        super(message);
    }
}
