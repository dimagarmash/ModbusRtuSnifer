package Mbus;

public class Function {
    String Number;
    byte number;
    String NumberWhenErr;
    byte numberWhenErr;
    String Description;

    int MinRequestByteLength ;



    boolean isFixedResponseLength;
    int MinResponseLength;

    boolean isFixedRequestLength;


    int StartDataPosInRequest;
    int StartDataPosInResponse;


    public Function(String number, byte number1, String numberWhenErr, byte numberWhenErr1, String description, int minRequestByteLength, boolean isFixedResponseLength, int minResponseLength, boolean isFixedRequestLength, int startDataPosInRequest, int startDataPosInResponse) {
        Number = number;
        this.number = number1;
        NumberWhenErr = numberWhenErr;
        this.numberWhenErr = numberWhenErr1;
        Description = description;
        MinRequestByteLength = minRequestByteLength;
        this.isFixedResponseLength = isFixedResponseLength;
        MinResponseLength = minResponseLength;
        this.isFixedRequestLength = isFixedRequestLength;
        StartDataPosInRequest = startDataPosInRequest;
        StartDataPosInResponse = startDataPosInResponse;
    }
}
