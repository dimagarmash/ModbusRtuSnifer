package Mbus;

public interface ControllerInterface {
    void SetText(String tx) throws Exception;
    void OpenPort();
    void ClosePort();
}
