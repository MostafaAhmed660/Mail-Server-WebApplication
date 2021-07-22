package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Importance;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.util.ArrayList;

public class OrMailsCriteria implements IMailsCriteria {
    IMailsCriteria firstCriteria;
    IMailsCriteria secondCriteria;
    public OrMailsCriteria(IMailsCriteria firstCriteria, IMailsCriteria secondCriteria){
        this.firstCriteria=firstCriteria;
        this.secondCriteria=secondCriteria;}

    @Override
    public ArrayList<Email> meetCriteria(ArrayList<Email> Emails) {
        ArrayList<Email> FirstDesiredEmails=firstCriteria.meetCriteria(Emails);
        ArrayList<Email> SeconDesiredEmails=secondCriteria.meetCriteria(Emails);
        for(Email E :SeconDesiredEmails){
            if(!FirstDesiredEmails.contains(E)){
                FirstDesiredEmails.add(E);
            }
        }
        return FirstDesiredEmails;
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
        e0.setImportance(Importance.Important);
        e1.setImportance(Importance.Important);
        e2.setImportance(Importance.low);
        e3.setImportance(Importance.Important);
        e.add(e0);
        e.add(e1);
        e.add(e2);
        e.add(e3);
        IMailsCriteria importance = new IMailsImportanceCriteria(Importance.low);
        IMailsCriteria sender = new IMailsSenderAddressCriteria("mohamed");
        IMailsCriteria or=new OrMailsCriteria(importance,sender);
        e = or.meetCriteria(e);
        for (Email E : e) {
            System.out.println(E.getSender());
        }
    }
}
