//Creating the necessary imports
import java.util.*;

public class Main {
    public static void main(String args[]) {
        // Creation of Scanner Object
        Scanner myObj = new Scanner(System.in);
    
        // Enter UEN and press Enter
        System.out.println("Enter UEN: ");
        // User input is automatically converted to uppercase
        String UEN = myObj.nextLine().toUpperCase();
    
        // Call the Function here
        UENVerification UENVfy = new UENVerification();
        UENVfy.UENVerifier(UEN);
        myObj.close();
      }
}
