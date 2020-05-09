package eg.edu.alexu.csd.datastructure;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

public class Mail implements Serializable {

    String username ;
    String email ;
    String password ;

    public void update() {
        File f = new File("parent/"+email+"/mail.txt");
        try { filemanage.writeObject(f,this); }
        catch (Exception e) {}
    }

    public void changePassword(String s){
        this.password = s ;
        update();
    }

    public boolean changeEmail(String s){
        File f = new File("parent/"+s);
        if (f.exists()){
            return false ;
        }
        else{
            File oldFile = new File("parent/"+email);
            oldFile.renameTo(f);
            this.email = s ;
            update();
            return true ;
        }
    }

    public static void make(String path){
        File f = new File(path);
        f.mkdir();
        f = new File(path+"/indexfile.txt");
        try{f.createNewFile();}catch (Exception e){}
    }

    public void createemil(){
        String path = "parent/"+email;
        File f = new File(path);
        f.mkdir();
        make(path+"/inbox");
        make(path+"/sent");
        make(path+"/trash");
        make(path+"/draft");

        f = new File(path+"/contact.txt");
        try{f.createNewFile();}catch (Exception e){}

        f = new File(path+"/content.txt");
        try{f.createNewFile();}catch (Exception e){}

        f = new File(path+"/mail.txt");
        try{f.createNewFile();}catch (Exception e){}
        update();
    }

    public int numberOfMessages(String s){
        File f = new File("parent/"+email+"/"+s);
        int counter = 0 ;
        for (File F : f.listFiles()){
            if (f.isFile())
                counter++;
        }
        return counter ;
    }

    public static String randomPassword(){
        String Upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ,
                Lower = "abcdefghijklmnopqrstuvwxyz" ,
                Special = "!@#$%^&*_-/|" ,
                Digit = "0123456789" ;
        String s = Upper + Lower + Special + Digit ;
        String password = "";
        char up = Upper.charAt((int)Math.round(Math.random()*25)),
                low = Lower.charAt((int)Math.round(Math.random()*25)),
                special = Special.charAt((int)Math.round(Math.random()*11)),
                digit = Digit.charAt((int)Math.round(Math.random()*9));
        password += Character.toString(up) + Character.toString(low) + Character.toString(special) + Character.toString(digit) ;

        //now that we are sure we have at least one character from each set of characters
        // at this stage, the password contains only 4 characters but
        //we want a 12-character password

        for (int i = 0 ; i < 8 ; i++){
            int index = (int)Math.round(Math.random()*61);
            password += s.charAt(index);
        }
        return password ;
    }

    public static String randomEmail(String username){
        String email = username ;
        String s = "0123456789";

        while(App.isexists(email)){
            for (int i = 0 ; i < 3 ; i++){
                int index = (int)Math.round(Math.random()*9);
                email += s.charAt(index);
            }
        }
        return email ;
    }

    public static String passwordStrenght(String password){
        String [] ans = {"very Weak","Not bad","Good","Excellent"};
        int  digit = 0 , special = 0 , upper = 0 , lower = 0 , a = 0 ;
        int l = password.length();
        for (int i = 0 ; i < l ; i++){
            char c = password.charAt(i);
            if (Character.isSpaceChar(c))
                special = 1;

            else if (Character.isDigit(c))
                digit = 1 ;

            else if (Character.isLowerCase(c))
                lower = 1;

            else if (Character.isUpperCase(c))
                upper = 1 ;

            else
                special = 1 ;

        }
        a = upper + lower + digit + special ;

        return ans[a-1];
    }
}
