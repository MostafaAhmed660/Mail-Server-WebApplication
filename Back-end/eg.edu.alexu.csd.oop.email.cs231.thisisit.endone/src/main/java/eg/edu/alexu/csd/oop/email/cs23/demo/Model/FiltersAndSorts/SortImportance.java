package eg.edu.alexu.csd.oop.email.cs23.demo.Model.FiltersAndSorts;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Importance;

import java.util.ArrayList;

public class SortImportance implements ISortMails {
    @Override
    public ArrayList<Email> SortedEmails(ArrayList<Email> Emails) {
        ArrayList<Importance>level=new ArrayList<>(0);
        level.add(Importance.low);
        level.add(Importance.medium);
        level.add(Importance.Important);
        level.add(Importance.veryImportant);
        ArrayList<Email>SortedMails=new ArrayList<>(0);
        for(int i=0;i<4;i++){
            for(int j=0;j<Emails.size();j++){
                if(Emails.get(j).getImportance().equals(level.get(i))){
                    SortedMails.add(Emails.get(j));
                }
            }
        }
        return SortedMails;
    }

}