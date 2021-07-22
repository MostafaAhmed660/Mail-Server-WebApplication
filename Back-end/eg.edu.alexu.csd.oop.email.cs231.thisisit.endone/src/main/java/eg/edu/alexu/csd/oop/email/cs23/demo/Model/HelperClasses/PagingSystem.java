package eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Importance;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts.*;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import javax.security.auth.Subject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PagingSystem {
    private User u;
    private ArrayList<Email>Emails;
    public ArrayList<Email> getEmails() {
        return Emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        Emails = emails;
    }

    public PagingSystem(User u, int UserEmails){this.u=u;
    this.Emails=this.u.getDefaultFolders().get(UserEmails).getEmailsList();}
    public ArrayList<Email>Filter(ArrayList<ArrayList<String>>Filters) {
        if(Filters!=null){
        for(int i=0;i<Filters.size();i++){
            for(int j=0;j<Filters.get(i).size()&& Filters.get(i) != null;j++) {
                if (Filters.get(i).get(0).equals("subject")) {
                    IMailsCriteria f = new IMailsSubjectCriteria(Filters.get(i).get(1));
                    this.Emails = f.meetCriteria(this.Emails);
                } else if (Filters.get(i).get(0).equals("sender")) {
                    IMailsCriteria f = new IMailsSenderNameCriteria(Filters.get(i).get(1));
                    this.Emails = f.meetCriteria(this.Emails);
                } else if (Filters.get(i).get(0).equals("SenderAddress")) {
                    IMailsCriteria f = new IMailsSenderAddressCriteria(Filters.get(i).get(1));
                    this.Emails = f.meetCriteria(this.Emails);
                } else if (Filters.get(i).get(0).equals("date")) {
                    try {
                        Date date1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(Filters.get(i).get(1));
                        IMailsCriteria f = new IMailsDateCriteria(date1);
                        this.Emails = f.meetCriteria(this.Emails);
                    } catch (ParseException e) {
                        return null;
                    }
                } else if (Filters.get(i).get(0).equals("subject")) {
                    IMailsCriteria f = new IMailsSubjectCriteria(Filters.get(i).get(1));
                    this.Emails = f.meetCriteria(this.Emails);
                } else if (Filters.get(i).get(0).equals("importance")) {
                    IMailsCriteria f = new IMailsImportanceCriteria(Importance.valueOf(Filters.get(i).get(1)));
                    this.Emails = f.meetCriteria(this.Emails);
                }
            }
        }
        return this.Emails;}
        else return this.Emails;
    }
    public ArrayList<Email>Sorts(String sort){
        if(sort==null){return this.Emails;}
        if(sort.equals("date")){
            ISortMails dateSort=new SortEmailDate();
            this.Emails=dateSort.SortedEmails(this.Emails);
            return this.Emails;
        }
        else if(sort.equals("sender")){
            ISortMails SenderSort=new SortEmailDate();
            this.Emails=SenderSort.SortedEmails(this.Emails);
            return this.Emails;
        }
        else if(sort.equals("subject")){
            ISortMails subjectSort=new SortEmailSubject();
            this.Emails=subjectSort.SortedEmails(this.Emails);
            return this.Emails;
        }
        else if(sort.equals("importance")){
            ISortMails ImportanceSort=new SortImportance();
            this.Emails=ImportanceSort.SortedEmails(this.Emails);
            return this.Emails;
        }
       return this.Emails;
    }

    public static void main(String[] args) {
        int[]a=new int[]{5,9,4};
        int[]b=new int[3];
        b=a;
        b[0]=9;
        System.out.println(b[0]);
        System.out.println(a[0]);

    }

}
