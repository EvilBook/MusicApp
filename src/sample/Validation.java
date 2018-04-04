package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation  {

    public static boolean mailValidation(String mail) {
        boolean status = false;
        String str = "masm";
        String mailPat = "^[A-Z0-9._%+-]+@[masm]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(mailPat);
        Matcher matcher = pattern.matcher(mailPat);

        if (matcher.matches()) {
            status = true;
        }else {
            status = false;
        }
        return status;
    }
}
