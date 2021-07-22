package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.ArrayListIterator;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Folder {
	private String name;
	private String path;
	private File file;
	private ArrayList<Email> emailsList=new ArrayList<Email>(0);


	public Folder(String name, String path, File file, ArrayList<Email> emailsList) {
		this.name = name;
		this.path = path;
		this.file = file;
		this.emailsList = emailsList;
	}

	public Folder() {
		emailsList = new ArrayList<Email>(); 
	}
	
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
	public void setEmailsList(ArrayList<Email> emailsList) {
		this.emailsList = emailsList;
	}
	
	public void addToEmailsList(Email email) {
		this.emailsList.add(email);
		File userFile = new File(this.path+"\\emails.xml");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(userFile);
			XMLEncoder encoder= new XMLEncoder(fos);
			encoder.writeObject(this.emailsList);
			encoder.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public ArrayListIterator getEmailsIterator() {
		return new ArrayListIterator(emailsList);
	}
	
	public ArrayList<Email> getEmailsList() {
		return emailsList;
	}
	
	

}
