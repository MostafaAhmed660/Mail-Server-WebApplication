package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortEmailSender implements ISortMails {
    @Override
    public ArrayList<Email> SortedEmails(ArrayList<Email> Emails) {
        ArrayList<Email>SortedMails=new ArrayList<Email>();
        for(int i=0;i<Emails.size();i++){SortedMails.add(Emails.get(i));}
        Collections.sort(SortedMails, new Comparator<Email>() {
            @Override
            public int compare(Email o1, Email o2) {
                return o1.getSender().compareTo(o2.getSender());
            }
        });
        return SortedMails;
}
    public static void main(String[] args) {
        ArrayList<Email> e = new ArrayList<>();
        User u = new User();
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        u.setUserName("mohamed");
        u1.setUserName("mohamed");
        u2.setUserName("ahmed");
        u3.setUserName("ahmed");
        Email e0 = new Email();
        Email e1 = new Email();
        Email e2 = new Email();
        Email e3 = new Email();
        e0.setSender(u.getEmailAddress());
        e1.setSender(u1.getEmailAddress());
        e2.setSender(u2.getEmailAddress());
        e3.setSender(u3.getEmailAddress());
        e0.setDate("01/21/2013 @03:13 PM");
        e1.setDate("01/21/2013 @04:37 PM");
        e2.setDate("01/21/2013 @10:41 AM");
        e3.setDate("01/21/2013 @10:48 AM");
        e.add(e0);
        e.add(e1);
        e.add(e2);
        e.add(e3);
        SortEmailSender S=new SortEmailSender();
        S.SortedEmails(e);
        for (Email E : e) {
            System.out.println(E.getSender());
        }
    }
}
