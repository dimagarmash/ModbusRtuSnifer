package Mbus;

public class Function {
    String Number;
    byte number;
    String Description;

    int RequestByteLength ;
    boolean isFixedResponseLength;
    int MinResponseLength;


    public Function(String number, byte number1, String description, int requestByteLength, boolean isFixedResponseLength, int minResponseLength) {
        Number = number;
        this.number = number1;
        Description = description;
        RequestByteLength = requestByteLength;
        this.isFixedResponseLength = isFixedResponseLength;
        MinResponseLength = minResponseLength;
    }
}
