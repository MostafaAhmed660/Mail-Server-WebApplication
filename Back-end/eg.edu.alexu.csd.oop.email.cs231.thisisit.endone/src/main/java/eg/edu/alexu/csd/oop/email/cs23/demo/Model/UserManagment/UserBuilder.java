package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserBuilder {
	
	private User u;
	
	
	
	public UserBuilder() {
		u = new User();
	}
	
	public void addEmailAddress(String emailAddress) {
		u.setEmailAddress(emailAddress);
	}
	
	public void addPass(String pass) {
		u.setPassWord(pass);
	}
	
	public void addUserName(String name) {
		u.setUserName(name);
	}
	
	public String getUser() {
		if(u.getEmailAddress()==null||u.getUserName()==null||u.getPassWord()==null){
			return "Please Complete Empty fields";
		}
		else {
			Path path=Paths.get("users\\"+u.getEmailAddress());
			if(Files.exists(path)){return "There's another account with tha same ID";}
			else { WithFiles.initUser(u);WithFiles.writeUser(u);return "Successfully created";}
		}
	}
	
	
}
