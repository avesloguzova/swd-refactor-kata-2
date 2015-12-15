package task1;

import org.junit.Before;
import org.junit.Test;
import task1.mock.TelemetryClientStateMock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TelemetryDiagnosticControlsTest {

    @Test
    public void testSuccessConnect() throws Exception {
        TelemetryClient mock = mock(TelemetryClient.class);
        when(mock.getOnlineStatus()).thenReturn(true);
        assertTrue(new TelemetryDiagnosticControls(mock).tryConnectClient());
    }

    @Test
    public void testFailConnect() throws Exception {
        TelemetryClient mock = mock(TelemetryClient.class);
        when(mock.getOnlineStatus()).thenReturn(false);
        assertFalse(new TelemetryDiagnosticControls(mock).tryConnectClient());
    }



    @Test
    public void testDisconnect() throws Exception {
        TelemetryClientStateMock telemetryClient = new TelemetryClientStateMock();
        telemetryClient.setOnlineStatus(true);
        TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(telemetryClient);
        telemetryDiagnosticControls.disconnectWithClient();
        assertFalse(telemetryClient.getOnlineStatus());
    }

    @Test
    public void testSendDiagnosticMessage() throws Exception {
        TelemetryClientStateMock telemetryClient = new TelemetryClientStateMock();
        telemetryClient.setOnlineStatus(true);//success connection
        TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(telemetryClient);
        telemetryDiagnosticControls.sendDiagnosticMessage();
        assertEquals(TelemetryClientStateMock.MOKED_DIAGNOSTIC_RESULT, telemetryClient.receive());
    }

    @Test
    public void testRecieveDiagnosticMessage() throws Exception {
        TelemetryClient mock = mock(TelemetryClient.class);
        when(mock.receive()).thenReturn(TelemetryClientStateMock.MOKED_DIAGNOSTIC_RESULT);
        assertEquals(TelemetryClientStateMock.MOKED_DIAGNOSTIC_RESULT,new TelemetryDiagnosticControls().receiveDiagnosticInfo());

    }

    @Test
    public void testcheckTransmission() throws Exception {
        TelemetryClientStateMock telemetryClient = new TelemetryClientStateMock();
        telemetryClient.setOnlineStatus(true);//success connection
        TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(telemetryClient);
        telemetryDiagnosticControls.checkTransmission();
        assertEquals(TelemetryClientStateMock.MOKED_DIAGNOSTIC_RESULT,telemetryDiagnosticControls.getDiagnosticInfo());

    }
}
