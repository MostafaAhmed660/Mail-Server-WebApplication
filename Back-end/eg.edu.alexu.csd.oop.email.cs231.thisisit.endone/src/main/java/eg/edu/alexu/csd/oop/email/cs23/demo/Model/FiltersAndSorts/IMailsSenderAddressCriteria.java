package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.util.ArrayList;

public class IMailsSenderAddressCriteria implements IMailsCriteria {
    private String sender;
    public IMailsSenderAddressCriteria(String sender){this.sender=sender;}
    public void SetCriteria(String sender){this.sender=sender;}
    @Override
    public ArrayList<Email> meetCriteria(ArrayList<Email> Emails) {
        ArrayList<Email> DesiredEmails=new ArrayList<Email>();
        for(int i=0;i<Emails.size();i++){
            if(Emails.get(i).getSender().equals(this.sender)){
                DesiredEmails.add(Emails.get(i));
            }
        }
        return DesiredEmails;
    }
    public static void main(String[] args) {
        ArrayList<Email> e=new ArrayList<>();
        User u=new User();
        User u1=new User();
        User u2=new User();
        User u3=new User();
        u.setEmailAddress("mohamed");
        u1.setEmailAddress("mohamed");
        u2.setEmailAddress("ahmed");
        u3.setEmailAddress("ahmed");
        Email e0=new Email();
        Email e1=new Email();
        Email e2=new Email();
        Email e3=new Email();
        e0.setSender(u.getEmailAddress());
        e1.setSender(u1.getEmailAddress());
        e2.setSender(u2.getEmailAddress());
        e3.setSender(u3.getEmailAddress());
        e.add(e0);
        e.add(e1);e.add(e2);e.add(e3);
        IMailsCriteria Sender=new IMailsSenderAddressCriteria("ahmed");
        e=Sender.meetCriteria(e);
        for(Email E :e){System.out.println(E.getSender());}

    }
}
