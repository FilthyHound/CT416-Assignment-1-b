import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author FilthyHound
 * @id 18321516
 */

public class DriverTest {
    private Driver target;

    @BeforeEach
    public void prepareTests() throws UnknownDateOfBirthException {
        target = spy(new Driver());
    }

    @AfterEach
    public void concludeTests(){
        target = null;
    }

    @Test
    public void testDriver() throws UnknownDateOfBirthException {
        target.init();

        verify(target, times(1)).init();
        verify(target, times(16)).computeAge(any(DateTime.class));
        verify(target, times(16)).computeDateOfBirth();
        verify(target, times(16)).generateRandomId();
        verify(target, times(32)).randIntInRange(anyInt(), anyInt());
    }

    @Test
    public void testDriver_ComputeAgeMethod_AsIntended() throws UnknownDateOfBirthException {
        DateTime testDateTime = new DateTime(1999, 12, 10, 0, 0);
        int answerAge = 21;
        int age = target.computeAge(testDateTime);

        assertEquals(age, answerAge);
    }

    @Test
    public void testDriver_ComputeAgeMethod_NullDateObject(){
        assertThrows(UnknownDateOfBirthException.class, () -> target.computeAge(null));
    }

    @Test
    public void testDriver_ComputeDateOfBirth_AsIntended(){
        DateTime testDateTime = target.computeDateOfBirth();

        verify(target, times(2)).randIntInRange(anyInt(), anyInt());

        assertNotNull(testDateTime);
        assertEquals(testDateTime.getClass(), DateTime.class);
    }
}
