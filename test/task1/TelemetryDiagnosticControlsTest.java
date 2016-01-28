package task1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TelemetryDiagnosticControlsTest {
    private static final String TEST_DIAGNOSTIC_INFO = "OK";
    private TelemetryClient clientMock;
    private TelemetryDiagnosticControls telemetryDiagnosticControls;

    @Before
    public void init() {
        clientMock = mock(TelemetryClient.class);
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(clientMock);
        when(clientMock.getOnlineStatus()).thenReturn(false).thenReturn(true);
    }

    @Test
    public void testDisconnectBeforeCheckingTransmission() throws Exception {
        doThrow(new RuntimeException()).when(clientMock).disconnect();
        try {
            telemetryDiagnosticControls.checkTransmission();
        } catch (RuntimeException e) {
            verify(clientMock).disconnect();
            verify(clientMock, never()).connect(TelemetryDiagnosticControls.DiagnosticChannelConnectionString);
        }
    }

    @Test
    public void testSuccessConnection() throws Exception {
        telemetryDiagnosticControls.checkTransmission();
        verify(clientMock).connect(TelemetryDiagnosticControls.DiagnosticChannelConnectionString);
    }

    @Test(expected = TelemetryClientConnectionException.class)
    public void testFailConnection() throws Exception {
        when(clientMock.getOnlineStatus()).thenReturn(false);
        telemetryDiagnosticControls.checkTransmission();
    }

    @Test
    public void testSuccessConnectionSecondAttempt() throws Exception {
        when(clientMock.getOnlineStatus()).thenReturn(false).thenReturn(false).thenReturn(true);
        telemetryDiagnosticControls.checkTransmission();
        verify(clientMock, times(2)).connect(TelemetryDiagnosticControls.DiagnosticChannelConnectionString);
    }

    @Test
    public void testSuccessConnectionThirdAttempt() throws Exception {
        when(clientMock.getOnlineStatus()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
        telemetryDiagnosticControls.checkTransmission();
        verify(clientMock, times(3)).connect(TelemetryDiagnosticControls.DiagnosticChannelConnectionString);
    }

    @Test
    public void testSendMessage() throws Exception {
        telemetryDiagnosticControls.checkTransmission();
        verify(clientMock).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
    }

    @Test
    public void testReceiveMessage() throws Exception {
        when(clientMock.receive()).thenReturn(TEST_DIAGNOSTIC_INFO);
        telemetryDiagnosticControls.checkTransmission();

        verify(clientMock).receive();
        assertEquals(telemetryDiagnosticControls.getDiagnosticInfo(), TEST_DIAGNOSTIC_INFO);
    }

    @Test
    public void testSuccessCheckTransmission() throws Exception {
        when(clientMock.receive()).thenReturn(TEST_DIAGNOSTIC_INFO);
        telemetryDiagnosticControls.checkTransmission();

        verify(clientMock).disconnect();
        verify(clientMock).connect(TelemetryDiagnosticControls.DiagnosticChannelConnectionString);
        verify(clientMock).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        verify(clientMock).receive();
        assertEquals(TEST_DIAGNOSTIC_INFO, telemetryDiagnosticControls.getDiagnosticInfo());
    }


}
