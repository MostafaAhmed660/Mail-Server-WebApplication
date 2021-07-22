package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ContactBuilder {
    private Contact c;
    public ContactBuilder(){this.c=new Contact();}
    public void SetContactName(String name){
        if(!(name==null))c.setName(name);}
    public void addEmailaddress(ArrayList<String> EmailAddress){
        for(int i=0;i<EmailAddress.size();i++){
            Path path= Paths.get("users\\"+EmailAddress.get(i));
            if(Files.exists(path)){c.addToEmailAddresses(EmailAddress.get(i));}
        }
    }
    public Contact getContact(){
        if((c.getEmailAddresses().size()==0)||(c.getName()==null)){
            return null;
        }
        else return c;

    }
}
