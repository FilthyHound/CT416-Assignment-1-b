import org.joda.time.DateTime;

/**
 * @author FilthyHound
 * @Id 18321516
 */

public class UnknownDateOfBirthException extends Exception{
    public UnknownDateOfBirthException(DateTime unknownBirthDate){
        super("ERROR: DateTime object: " + unknownBirthDate + ", is null");
    }
}
