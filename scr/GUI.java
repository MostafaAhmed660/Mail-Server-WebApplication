package eg.edu.alexu.csd.datastructure;

import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GUI {

    @FXML TextField email ;
    @FXML PasswordField password ;
    @FXML PasswordField passwordSignup ;
    @FXML PasswordField passwordConfirmSignup ;
    @FXML Text passwordStrength ;
    @FXML Text randomPassword ;
    @FXML TextField emailSignup ;
    @FXML TextField usernameSignup ;
    @FXML Text randomEmail ;

    //emailview component
    @FXML Text welcome ;

    @FXML public void checkEmail(){
        String s = email.getText();
        if (s.isEmpty())
            try {
                Main.errorPopupWindow();
                return;}
        catch (Exception e) {}

        if (App.isexists(s)){
            Main.m.email = s ;
            passwordpage();
        }
        else
            try { Main.errorPopupWindow(); } catch (Exception e) {}
    }

    @FXML public void getpassword(){
        String s = password.getText();
        if (App.signin(Main.m.email,s)) {
            Main.m = App.getMail(Main.m.email);
            try { Main.Emailview(); } catch (Exception e) {}
        }
        else{
            try { Main.errorPopupWindow(); } catch (Exception e) {}
        }
    }

    @FXML public void passwordpage() { try { Main.passwordPage(); } catch (Exception e) {} }

    @FXML public void homepage() { try { Main.homePage(); } catch (Exception e) {} }

    @FXML public void signuppage() { try { Main.SignupPage(); } catch (Exception e) {} }

    @FXML public void closeErrorPopup() { try { Main.error.close(); } catch (Exception e) {} }

    @FXML public void randomPassword() {
        String s = Mail.randomPassword();
        randomPassword.setText(s);
    }

    @FXML public void passwordStrength() {
        String pass1 = passwordSignup.getText(), pass2 = passwordConfirmSignup.getText();
        if (!pass1.equals(pass2))
            try { Main.errorPopupWindow(); } catch (Exception e) {}
        else{
            passwordStrength.setText(Mail.passwordStrenght(pass1));
        }
    }
    public boolean checkhascharacter(String s){
        int l = s.length();
        for (int i = 0 ; i < l ; i++){
            if(Character.isLetter(s.charAt(i)))
                return true;
        }
        return false ;
    }

    @FXML public void randomEmail() {
        String s = usernameSignup.getText();
        if(s.isEmpty() || !checkhascharacter(s))
            try { Main.errorPopupWindow(); } catch (Exception e) {}
        else
            randomEmail.setText(Mail.randomEmail(s));
    }

    @FXML public void setWelcome(){
        welcome.setText(Main.m.username);
    }

}
