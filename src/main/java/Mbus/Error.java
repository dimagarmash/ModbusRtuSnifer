package Mbus;

public class Error {
    byte Number;
    String RestrictedDescription;
    String FullDescription;


    public Error(byte number, String restrictedDescription,String fullDescription) {
        Number = number;
        RestrictedDescription = restrictedDescription;
        FullDescription=fullDescription;
    }
}
