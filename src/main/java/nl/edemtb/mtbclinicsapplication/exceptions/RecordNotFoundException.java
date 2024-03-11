package nl.edemtb.mtbclinicsapplication.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {

        super();

    }

    // De exception met message
    public RecordNotFoundException(String message) {

        super(message);

    }

}
