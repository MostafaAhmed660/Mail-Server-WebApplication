package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.ArrayListIterator;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact {

	private String name;
	private ArrayList<String> emailAddresses;


	public Contact() {
		emailAddresses = new ArrayList<String>();
	}
	public Contact(String name, ArrayList<String> emailAddresses) {
		this.name = name;
		this.emailAddresses = emailAddresses;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmailAddresses(ArrayList<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	public void addToEmailAddresses(String emailAddress) {
		emailAddresses.add(emailAddress);
	}
	//for easiness
	public ArrayList<String> getEmailAddresses() {
		return emailAddresses;
	}
	
	
	
	
	
	
}
