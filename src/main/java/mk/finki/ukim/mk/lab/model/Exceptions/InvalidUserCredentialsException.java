package mk.finki.ukim.mk.lab.model.Exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid user creditentials exception");
    }

}
