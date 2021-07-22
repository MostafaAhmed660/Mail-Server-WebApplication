package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;
import java.util.Date;

public class IMailsDateCriteria implements IMailsCriteria {
    private Date date;
    public IMailsDateCriteria(Date date){this.date=date;}
    public void SetCriteria(Date date){this.date=date;}
    @Override
    public ArrayList<Email> meetCriteria(ArrayList<Email> Emails) {
        ArrayList<Email> DesiredEmails=new ArrayList<Email>();
        for(int i=0;i<Emails.size();i++){
            if(Emails.get(i).getSender().equals(this.date)){
                DesiredEmails.add(Emails.get(i));
            }
        }
        return DesiredEmails;
    }
}
