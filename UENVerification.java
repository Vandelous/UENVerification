//Creating the necessary imports
import java.util.*;

public class UENVerification {

  static HashSet<String> UENHash;
  static HashMap<String, Integer> YearCheck = new HashMap<>();

  public UENVerification() {
    // Create array storing the existing PQ Values.
    String[] PQList = { "LP", "LL", "FC", "PF", "RF",
        "MQ", "MM", "NB", "CC", "CS",
        "MB", "FM", "GS", "DP", "CP",
        "NR", "CM", "CD", "MD", "HS",
        "VH", "CH", "MH", "CL", "XL",
        "CX", "HC", "RP", "TU", "TC",
        "FB", "FN", "PA", "PB", "SS",
        "MC", "SM", "GA", "GB" };

    // Use a hash for O1 access time to reduce the overall runtime.
    UENHash = new HashSet<>(Arrays.asList(PQList));
    YearCheck.put("T", 2000);
    YearCheck.put("S", 1900);
    YearCheck.put("R", 1800);
  }

  boolean DigitChecker(String value) {
    if (value.matches("[0-9]+") == true) {
      return true;
    } else {
      return false;
    }
  }

  boolean AlphabetChecker(String value) {
    if (value.matches("[a-zA-Z]+") == true) {
      return true;
    } else {
      return false;
    }
  }

  void UENVerifier(String UEN) {

    boolean Verification = false;

    // Checking for TypeA
    if (UEN.length() == 9) {
      // Split the String for authentication
      String split1 = UEN.substring(0, 8);
      String split2 = UEN.substring(8, 9);
      boolean digitschecker = split1.matches("[0-9]+");
      boolean alphabetschecker = split2.matches("[a-zA-Z]+");

      // If all conditions are met
      if (digitschecker == true && alphabetschecker == true) {
        Verification = true;
      }
    }

    // Check for TypeB or TypeC
    else if (UEN.length() == 10) {
      String PQCheck = UEN.substring(3, 5);
      boolean checker = false;
      Calendar cal = Calendar.getInstance();
      int currentyear = cal.get(Calendar.YEAR);
      int UENYear;

      if (UENHash.contains(PQCheck)) {
        checker = true;
      }

      // Type C UEN Verifier, TyyPQnnnnX
      if (checker) {
        try {
          // Split the String for authentication
          String TypeCSplit1a = UEN.substring(0, 1);
          String TypeCSplit1b = UEN.substring(1, 3);
          String TypeCSplit2 = UEN.substring(5, 9);
          String TypeCSplit3 = UEN.substring(9, 10);
          boolean yearchecker = false;

          if (YearCheck.containsKey(TypeCSplit1a)) {
            UENYear = Integer.parseInt(TypeCSplit1b);
            int UENCurYear = UENYear + YearCheck.get(TypeCSplit1a);
            if (UENCurYear <= currentyear)
            {
              yearchecker = true;
            }
          }

          // If all conditions are met
          if (AlphabetChecker(TypeCSplit1a) == true &&
              DigitChecker(TypeCSplit1b) == true &&
              DigitChecker(TypeCSplit2) == true &&
              AlphabetChecker(TypeCSplit3) == true &&
              yearchecker == true) {
            Verification = true;
          }

        } catch (NumberFormatException ex) {
          System.out.println("Please Enter A Valid UEN Number.");
        }
      }

      // Type B UEN Verifier, yyyynnnnnX
      else {
        try {
          // Split the String for authentication
          String TypeBSplit1 = UEN.substring(0, 4);
          String TypeBSplit2 = UEN.substring(4, 9);
          String TypeBSplit3 = UEN.substring(9, 10);
          UENYear = Integer.parseInt(TypeBSplit1);

          // Check if all conditions are met for Type B UEN Code
          if (DigitChecker(TypeBSplit1) == true &&
              DigitChecker(TypeBSplit2) == true &&
              AlphabetChecker(TypeBSplit3) == true &&
              UENYear <= currentyear) {
            Verification = true;
          }
        } catch (NumberFormatException ex) {
          System.out.println("Please Enter A Valid UEN Number.");
        }
      }

      if (Verification == false) {
        System.out.println("Please Enter A Valid UEN Number.");
      } else {
        System.out.println("You Have Successfully Enter A Valid UEN Number. ");
      }
    }
  }
}
