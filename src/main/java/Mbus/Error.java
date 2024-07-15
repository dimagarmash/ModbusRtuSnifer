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
        return "Error" +
                "\n\t\t{ Number=" + Number +
                "\n\t \t, RestrictedDescription='" + RestrictedDescription + '\'' +
                "\n\t \t, FullDescription='" + FullDescription + '\'' +
                "\n\t \t}";
    }

    public Error(byte number, String restrictedDescription, String fullDescription) {
        Number = number;
        RestrictedDescription = restrictedDescription;
        FullDescription=fullDescription;
    }
}
