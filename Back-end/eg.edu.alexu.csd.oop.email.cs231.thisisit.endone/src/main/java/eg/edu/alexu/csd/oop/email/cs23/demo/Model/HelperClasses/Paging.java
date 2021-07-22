package eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;

public interface Paging {
	public ArrayList<Email> getPage(int countOfEmails, int pageNumber, ArrayList<Email>ShowingEmails);
}
