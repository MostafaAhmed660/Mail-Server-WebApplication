package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;

public interface ISortMails {
    public ArrayList<Email> SortedEmails(ArrayList<Email> Emails);
}
