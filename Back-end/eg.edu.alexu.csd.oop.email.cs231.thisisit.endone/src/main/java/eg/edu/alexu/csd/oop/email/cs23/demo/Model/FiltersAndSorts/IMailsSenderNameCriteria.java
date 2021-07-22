package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;

public class IMailsSenderNameCriteria implements IMailsCriteria {
    private String senderName;
    public IMailsSenderNameCriteria(String sender){this.senderName=sender;}
    public void SetCriteria(String sender){this.senderName=sender;}
    @Override
    public ArrayList<Email> meetCriteria(ArrayList<Email> Emails) {
        ArrayList<Email> DesiredEmails=new ArrayList<Email>();
        for(int i=0;i<Emails.size();i++){
            if(Emails.get(i).getSender().equals(this.senderName)){
                DesiredEmails.add(Emails.get(i));
            }
        }
        return DesiredEmails;
    }
}
