package Mbus;

public class Error {
    byte Number;
    String RestrictedDescription;
    String FullDescription;

    @Override
    public String toString() {
        return "Error{" +
                "Number=" + Number +
                ", RestrictedDescription='" + RestrictedDescription + '\'' +
                ", FullDescription='" + FullDescription + '\'' +
                '}';
    }

    public Error(byte number, String restrictedDescription, String fullDescription) {
        Number = number;
        RestrictedDescription = restrictedDescription;
        FullDescription=fullDescription;
    }
}
