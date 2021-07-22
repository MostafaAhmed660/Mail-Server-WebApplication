package eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.Contact;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.util.ArrayList;

public class PageAdapter implements Paging{
	//for retrieving pages


	@Override
	public ArrayList<Email> getPage(int countOfEmails, int pageNumber, ArrayList<Email>ShowingEmails) {
		//here we call the function and return the page
		//also we need the choice to get the suitable Folder inbox and ....
		//from page 1 to ...
		if(ShowingEmails.size()==0){return ShowingEmails;}
		int start = (pageNumber - 1) * countOfEmails + 1;
		int end = start + countOfEmails - 1;
		if((end>ShowingEmails.size())){
		end=end-(end-ShowingEmails.size());}
		ArrayList<Email> list = new ArrayList<Email>();
		for (int i = start - 1; i < end; i++)
			list.add(ShowingEmails.get(i));
		return list;
	}
	public ArrayList<Contact>getContactsPage(int countofContacts, int pageNumber, ArrayList<Contact>contacts){
		int start = (pageNumber - 1) * countofContacts + 1;
		int end = start + countofContacts - 1;
		if(end>contacts.size()){
			end=end-(end-contacts.size());}
		ArrayList<Contact> list = new ArrayList<Contact>();
		for (int i = start - 1; i < end; i++)
			list.add(contacts.get(i));
		return list;
	}

	

}
