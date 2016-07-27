
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.EOFException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


public class MainTest {
    private Main main;
    @Mock
    private ScannerHelper scannerHelper;
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        main = new Main();
        main.setScannerHelper(scannerHelper);
    }

    @Test
    public void test() {
        // Add event
        when(scannerHelper.readString("Enter name of event: ")).thenReturn("event");
        when(scannerHelper.readInt("Attempts allowed: ")).thenReturn(1);
        when(scannerHelper.readString("Bigger better? (yes/no): ")).thenReturn("yes");
        main.addEvent();

        addParticipantAndData(100, 3.0);
        addParticipantAndData(101, 2.0);
        addParticipantAndData(102, 1.0);

        main.resultTeam();

        exit.expectSystemExit();
        main.exit();

    }

    private void addParticipantAndData(int id, double result) {
        // Add participant 1
        when(scannerHelper.readString("Enter participants first name: ")).thenReturn("firstname" + id);
        when(scannerHelper.readString("Enter participants last name: ")).thenReturn("lastname" + id);
        when(scannerHelper.readString("Enter participants team: ")).thenReturn("team" + id);
        main.addParticipant();

        // Add data for participant 1
        when(scannerHelper.readInt("Enter participants ID: ")).thenReturn(id);
        when(scannerHelper.readString("Enter event: ")).thenReturn("event");
        when(scannerHelper.readDouble(anyString())).thenReturn(result);

        main.addResultParticipant();
    }

}
