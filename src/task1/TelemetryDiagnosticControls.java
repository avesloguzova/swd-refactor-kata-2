package task1;

public class TelemetryDiagnosticControls {
    public static final String DiagnosticChannelConnectionString = "*111#";

    private final TelemetryClient telemetryClient;
    private String diagnosticInfo = "";

    public TelemetryDiagnosticControls() {
        telemetryClient = new TelemetryClient();
    }

    public TelemetryDiagnosticControls(TelemetryClient telemetryClient) {
        this.telemetryClient = telemetryClient;
    }

    public String getDiagnosticInfo() {
        return diagnosticInfo;
    }

    public void checkTransmission() throws TelemetryClientConnectionException {
        disconnectFromClient();

        if (!tryConnectClient()) {
            throw new TelemetryClientConnectionException("Unable to connect.");
        }
        sendDiagnosticMessage();
        diagnosticInfo = receiveDiagnosticInfo();
    }

    private String receiveDiagnosticInfo() {
        return telemetryClient.receive();
    }

    private void sendDiagnosticMessage() {
        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
    }

    private boolean tryConnectClient() {
        int retryLeft = 3;
        while (!telemetryClient.getOnlineStatus() && retryLeft > 0) {
            telemetryClient.connect(DiagnosticChannelConnectionString);
            retryLeft -= 1;
        }
        return telemetryClient.getOnlineStatus();
    }

    private void disconnectFromClient() {
        diagnosticInfo = "";
        telemetryClient.disconnect();
    }

}