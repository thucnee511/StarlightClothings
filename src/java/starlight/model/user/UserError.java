/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package starlight.model.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class UserError {

    private String loginError;
    private String signupError;

    public UserError() {
        this.loginError = "";
        this.signupError = "";
    }

    public UserError(String loginError, String signupError) {
        this.loginError = loginError;
        this.signupError = signupError;
    }

    public String getLoginError() {
        return loginError;
    }

    public void addLoginError(String loginError) {
        this.loginError += loginError;
    }

    public String getSignupError() {
        return signupError;
    }

    public void addSignupError(String signupError) {
        this.signupError += signupError;
    }

    public static UserError validSignupInfo(String password, String confirmPassword,
            String email) {
        String errorMsg = "";
        if (!isValidEmail(email)) {
            errorMsg += "Invalid email format!!!\n";
        }
        if (password.length() < 8) {
            errorMsg += "Password must have at least 8 characters.\n";
        }
        if (!password.equals(confirmPassword)) {
            errorMsg += "Confirm password does not match\n";
        }
        return !errorMsg.isEmpty() ? new UserError("", errorMsg) : null;
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String toString() {
        return loginError + '\n' + signupError;
    }
}
