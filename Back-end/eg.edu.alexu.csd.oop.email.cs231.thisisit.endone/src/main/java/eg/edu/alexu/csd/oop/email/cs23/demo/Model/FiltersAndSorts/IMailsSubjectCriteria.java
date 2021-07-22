package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;

import java.util.ArrayList;

public class IMailsSubjectCriteria implements IMailsCriteria {
    private String Subject;
    public IMailsSubjectCriteria(String Subject){this.Subject=Subject;}
    public void SetCriteria(String Subject){this.Subject=Subject;}
    @Override
    public ArrayList<Email> meetCriteria(ArrayList<Email> Emails) {
        ArrayList<Email> DesiredEmails=new ArrayList<Email>();
        for(int i=0;i<Emails.size();i++){
            if(Emails.get(i).getSubject().equals(this.Subject)){
                DesiredEmails.add(Emails.get(i));
            }
        }
        return DesiredEmails;
    }
}
