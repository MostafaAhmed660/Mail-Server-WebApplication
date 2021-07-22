package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortContacts {
    public ArrayList<Contact> SortedEmails(ArrayList<Contact> contacts) {
        ArrayList<Contact>SortedContacts=new ArrayList<Contact>();
        for(int i=0;i<contacts.size();i++){SortedContacts.add(contacts.get(i));}
        Collections.sort(SortedContacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return SortedContacts;
}
}
