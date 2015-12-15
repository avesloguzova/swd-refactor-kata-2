package task1.mock;

import task1.TelemetryClient;

/**
 * Created by av on 15/12/15.
 */
public class TelemetryClientStateMock extends TelemetryClient{
    public static final String MOKED_DIAGNOSTIC_RESULT = "OK";

    public void setOnlineStatus(boolean status) {
        onlineStatus = status;
    }

    @Override
    public void send(String message) {
        if (message == null || "".equals(message))
        {
            throw new IllegalArgumentException();
        }
        diagnosticMessageResult = MOKED_DIAGNOSTIC_RESULT;
    }
}
