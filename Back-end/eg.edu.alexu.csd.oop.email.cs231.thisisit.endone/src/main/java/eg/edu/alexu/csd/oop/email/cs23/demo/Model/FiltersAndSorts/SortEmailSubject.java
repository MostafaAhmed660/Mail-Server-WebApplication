package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortEmailSubject implements ISortMails {
    @Override
    public ArrayList<Email> SortedEmails(ArrayList<Email> Emails) {
        ArrayList<Email>SortedMails=new ArrayList<Email>();
        for(int i=0;i<Emails.size();i++){SortedMails.add(Emails.get(i));}
        Collections.sort(SortedMails, new Comparator<Email>() {
            @Override
            public int compare(Email o1, Email o2) {
                return o1.getSubject().compareTo(o2.getSubject());
            }
        });
        return SortedMails;
    }
}
