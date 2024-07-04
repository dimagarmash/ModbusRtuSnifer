package Mbus;

public class Error {
    byte Number;
    String RestrictedDescription;
    String FullDescription;

    public Error() {
        Number=0;
        RestrictedDescription="NoError";
        FullDescription="NoError";
    }

    @Override
    public String toString() {
        return "Error{" +
                "\n\t Number=" + Number +
                "\n\t , RestrictedDescription='" + RestrictedDescription + '\'' +
                "\n\t , FullDescription='" + FullDescription + '\'' +
                '}';
    }

    public Error(byte number, String restrictedDescription, String fullDescription) {
        Number = number;
        RestrictedDescription = restrictedDescription;
        FullDescription=fullDescription;
    }
}
