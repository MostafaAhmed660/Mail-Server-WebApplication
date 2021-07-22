package eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.io.File;
import java.util.ArrayList;

public interface EmailSender {

	public void addSubject(String subject);
	
	public void addBody(String body);
	
	public void addAttachement(File f);
	
	public ArrayList<String> send(Email e,String sender, ArrayList<String> reciever, boolean send);
	
	
	
}
