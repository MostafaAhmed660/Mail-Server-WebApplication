package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;

public interface IMailsCriteria {
    //function to get new list that meet criteria according to string s
    public ArrayList<Email>meetCriteria(ArrayList<Email> Emails);
}
