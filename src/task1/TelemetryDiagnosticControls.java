package task1;

public class TelemetryDiagnosticControls
{
  private final String DiagnosticChannelConnectionString = "*111#";

  private final TelemetryClient telemetryClient;
  private String diagnosticInfo = "";

  public TelemetryDiagnosticControls()
  {
    telemetryClient = new TelemetryClient();
  }

  public TelemetryDiagnosticControls(TelemetryClient telemetryClient) {
    this.telemetryClient = telemetryClient;
  }

  public String getDiagnosticInfo(){
    return diagnosticInfo;
  }

  public void setDiagnosticInfo(String diagnosticInfo){
    this.diagnosticInfo = diagnosticInfo;
  }

  public void checkTransmission() throws Exception
  {
    disconnectWithClient();

    if(!tryConnectClient()){
      throw new Exception("Unable to connect.");
    }
    sendDiagnosticMessage();
    diagnosticInfo = receiveDiagnosticInfo();
  }

  public String receiveDiagnosticInfo() {
    return telemetryClient.receive();
  }

  public void sendDiagnosticMessage() {
    telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
  }

  public boolean tryConnectClient()  {
    int retryLeft = 3;
    while (!telemetryClient.getOnlineStatus() && retryLeft > 0)
    {
      telemetryClient.connect(DiagnosticChannelConnectionString);
      retryLeft -= 1;
    }

    return telemetryClient.getOnlineStatus();
  }

  public void disconnectWithClient() {
    diagnosticInfo = "";

    telemetryClient.disconnect();
  }

}