package eg.edu.alexu.csd.datastructure;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App{

    public static boolean isexists(String email){
        File f = new File("parent/"+email);
        if (f.exists())
            return true ;
        else
            return false ;
    }

    public static boolean signin(String email,String password){
        Mail m = App.getMail(email);
        if (password.equals(m.password))
            return true ;
        else
            return false;
    }

    // read mail.txt file that have Mail object which have Username , email & password
    public static Mail getMail(String email){
        File f = new File("parent/"+email+"/mail.txt");
        Mail m = new Mail() ;
        try {
            m = (Mail)filemanage.readObject(f);
        }catch (Exception e){}
        return  m ;
    }


}
