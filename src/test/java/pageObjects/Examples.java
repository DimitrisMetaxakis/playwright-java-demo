package pageObjects;

import javax.xml.stream.events.Characters;
import java.util.Locale;

public class Examples {

    public String capitalizeLetters(String s) {
        s.toLowerCase();
        char first = Character.toUpperCase(s.charAt(0));
        char last = Character.toUpperCase(s.charAt(s.length()-1));

        String middle = s.substring(1 ,s.length()-1);

        return first + middle + last;
    }

    public boolean isAnagram(String a) {
        a = a.toLowerCase();
        int left = 0;
        int right = a.length()-1;
        while (left < right){
            if (a.charAt(left) != a.charAt(right)){
                return false;

            }
            left++;
            right--;
        }
        return true;
    }

    public String printSomething(int n) {
        String result = "";
      if (n % 3 == 0){
          result =  "foo";
      }
      else if (n % 5 ==0){
          result = "buzz";
      }
      else if (n % 2 == 0){
          result =  "foo + buzz";
      }
        return result;

    }


}




