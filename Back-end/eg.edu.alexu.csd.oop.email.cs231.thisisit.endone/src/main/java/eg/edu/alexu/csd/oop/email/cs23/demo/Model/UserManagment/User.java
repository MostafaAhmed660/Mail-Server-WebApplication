package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.ArrayListIterator;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class User {
	private String userName;
	private String emailAddress;
	private String passWord;
	
	private ArrayList<Contact> contacts;
	private ArrayList<Folder> defaultFolders;
	private ArrayList<Folder> userFolders;

	
	
	
	public User() {
		defaultFolders = new ArrayList<Folder>();
		userFolders = new ArrayList<Folder>();
		contacts = new ArrayList<Contact>();
	}
	
	
	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void addToContacts(Contact c) {
		contacts.add(c);
	}
		
	public void addToDefaultFolders(Folder f) {
		defaultFolders.add(f);
	}
	
	public void addToUserFolders(Folder f) {
		userFolders.add(f);
	}
	
	
	
	
	//to ease the use only
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public ArrayList<Folder> getDefaultFolders() {
		return defaultFolders;
	}

	public ArrayList<Folder> getUserFolders() {
		return userFolders;
	}



	
	public ArrayListIterator getContactIterator(){
		return new ArrayListIterator(contacts);	
	}
	
	public ArrayListIterator getDefaultFoldersIterator(){
		return new ArrayListIterator(defaultFolders);	
	}

	public ArrayListIterator getUserFoldersIterator(){
		return new ArrayListIterator(userFolders);	
	}


	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setDefaultFolders(ArrayList<Folder> defaultFolders) {
		this.defaultFolders = defaultFolders;
	}

	public void setUserFolders(ArrayList<Folder> userFolders) {
		this.userFolders = userFolders;
	}

	public ArrayListIterator getPageFromDefault(int start, int end, int folderInd) {
		//returns a page of emails
		Folder f = defaultFolders.get(folderInd);
		ArrayList<Email> list = new ArrayList<Email>();
		
		for (int i = start - 1; i < end; i++)
			list.add(f.getEmailsList().get(i));
		
		
		return new ArrayListIterator(list);
	}
	
	

	
	
	
	public boolean deleteContact(String name) {
		// -1 if not found
		// otherwise returns index of the contact in the contacts
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getName().equals(name)) {
				contacts.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void editContact(Contact c,String oldContactName) {
		//you may delete the original one and reAdd the new one in its place 
		// here you must know the details of the old contact and copy what you want to the new one
		for(int i=0;i<this.contacts.size();i++){
			if(this.contacts.get(i).getName()==oldContactName){
				this.contacts.set(i,c);
			}
		}
	}

	//with trash and back from it
	// 1 for default
	// 2 for user
	// secondIndex == 3 if you want to gotrash
	public String MoveEmailWithdelete(int defaultOrUser, int index, int defaultOrUserForSecond, int secondIndex, int id) {
		ArrayList<Email> emailsList;
		ArrayList<Email> secondList = null;

		Email email = null;
		if (defaultOrUser == 1) {
			emailsList = defaultFolders.get(index).getEmailsList();

			for (int i = 0; i < emailsList.size(); i++)
				if (emailsList.get(i).getId() == id) {
					email = emailsList.remove(i);
					break;
				}
		} else if (defaultOrUser == 2) {
			emailsList = userFolders.get(index).getEmailsList();

			for (int i = 0; i < emailsList.size(); i++)
				if (emailsList.get(i).getId() == id) {
					email = emailsList.remove(i);
					break;
				}
		}

		//which folder
		if (defaultOrUserForSecond == 1)
			secondList = defaultFolders.get(secondIndex).getEmailsList();
		else if (defaultOrUserForSecond == 2)
			secondList = userFolders.get(secondIndex).getEmailsList();

		//deleted email

		if (email == null)
			return "email is not found";
		else {
			if (defaultOrUserForSecond == 1 && secondIndex == 2)
				email.setPreviousPositionForTrashedObj(defaultFolders.get(index).getName());
			secondList.add(email);
			return "email added successfully";
			//email.addToHistory();
			// add the current position to detect moving to previous place
		}




		//rewrite the two users or the two folders or the two emailsLists

	}

	//with user's folders and back from it also for bulk move
	public String MoveEmailWithoutdelete(int defaultOrUser, int index, int defaultOrUserForSecond, int secondIndex, int id) {
		ArrayList<Email> emailsList;
		ArrayList<Email> secondList = null;

		Email email = null;
		if (defaultOrUser == 1) {
			emailsList = defaultFolders.get(index).getEmailsList();

			for (int i = 0; i < emailsList.size(); i++)
				if (emailsList.get(i).getId() == id) {
					email = emailsList.get(i);
					break;
				}

		} else if (defaultOrUser == 2) {
			emailsList = userFolders.get(index).getEmailsList();
			for (int i = 0; i < emailsList.size(); i++)
				if (emailsList.get(i).getId() == id) {
					email = emailsList.get(i);
					break;
				}
		}

		//which folder
		if (defaultOrUserForSecond == 1)
			secondList = defaultFolders.get(secondIndex).getEmailsList();
		else if (defaultOrUserForSecond == 2)
			secondList = userFolders.get(secondIndex).getEmailsList();


		if (email == null)
			return "email is not found";
		else {
			secondList.add(email.clone());
			return "email added successfully";
		}


		//rewrite the two users or the two folders or the two emailsLists

	}



}
