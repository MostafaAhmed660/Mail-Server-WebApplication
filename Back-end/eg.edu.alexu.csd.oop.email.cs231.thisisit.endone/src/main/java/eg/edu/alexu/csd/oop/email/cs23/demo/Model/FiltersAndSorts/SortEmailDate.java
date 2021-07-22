package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortEmailDate implements ISortMails {
    @Override
    public ArrayList<Email> SortedEmails(ArrayList<Email> Emails) {
        ArrayList<Email>SortedMails=new ArrayList<Email>();
        for(int i=0;i<Emails.size();i++){SortedMails.add(Emails.get(i));}
        Collections.sort(SortedMails, new Comparator<Email>() {
            DateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            @Override
            public int compare(Email o1, Email o2) {
                try {
                    return f.parse(o2.getDate()).compareTo(f.parse(o1.getDate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
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
        u.setEmailAddress("mohamed");
        u1.setEmailAddress("mohamed");
        u2.setEmailAddress("ahmed");
        u3.setEmailAddress("ahmed");
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
        SortEmailDate S=new SortEmailDate();
        S.SortedEmails(e);
        for (Email E : e) {
            System.out.println(E.getDate());
        }
    }
    }
