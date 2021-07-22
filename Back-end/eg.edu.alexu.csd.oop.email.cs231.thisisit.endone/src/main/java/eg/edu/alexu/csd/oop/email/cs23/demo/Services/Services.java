package eg.edu.alexu.csd.oop.email.cs23.demo.Services;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Importance;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserFcade.UserFacade;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.Contact;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Arrays;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class Services {
    User u;
    ArrayList<Email>EmailsToshow;
    ArrayList<Contact>ContactsToshow;
    int DefaultEmailsIndx=0;
    public String login(String emailaddress,String password){
        UserFacade uf=new UserFacade();
        String Access=uf.logIn(emailaddress,password);
        u=uf.u;
        return Access;
    }
    public String getUser(){
        if(u==null){return "User Undefined";}
        EmailsToshow=new UserFacade(u).SetEmailsToShow(u,DefaultEmailsIndx);
        ContactsToshow=new UserFacade(u).userContacts();
        return u.getUserName();
    }

    public String getUseraddress(){
        if (u == null)
            return "Useraddress undefined !";
        return u.getEmailAddress();
    }

    public String SignUp(String emailaddress,String password,String username){
        UserFacade uf=new UserFacade();
        return uf.signUp(emailaddress,password,username);
    }
    public ArrayList<String> Compose(Email e, boolean send){
        UserFacade uf=new UserFacade(u);
        ArrayList<String> inform=uf.composeEmail(e,send);
        this.u=uf.u;
        return inform;
    }
    public String addContact(Contact c){
        UserFacade uf=new UserFacade(u);
        String inform=uf.addContact(c);
        u=uf.u;
        getUser();
        ContactsToshow=new UserFacade(u).userContacts();
        return inform;
    }
    public String editContact(Contact c,String oldName){
        UserFacade uf=new UserFacade(u);
        String inform=uf.editContact(c,oldName);
        u=uf.u;
        ContactsToshow=new UserFacade(u).userContacts();
        return inform;
    }
    public String removeContact(String name){
        UserFacade uf=new UserFacade(u);
        String inform=uf.removeContact(name);
        u=uf.u;
        ContactsToshow=new UserFacade(u).userContacts();
        return inform;
    }
    public ArrayList<Email> SetEmailsToShow(int EmailsInd){
        this.DefaultEmailsIndx=EmailsInd;
        UserFacade uf=new UserFacade(u);
        EmailsToshow=uf.SetEmailsToShow(u,DefaultEmailsIndx);
        return getEmailsPage(1);
    }
    public ArrayList<Email>FilterAndSorts(ArrayList<ArrayList<String>>Filters,String Sort){
        UserFacade uf=new UserFacade(u);
        EmailsToshow=uf.FilterAndSorts(u,DefaultEmailsIndx,Filters,Sort);
        return uf.GetPage(10,1,EmailsToshow);
    }
    public ArrayList<Email>getEmailsPage(int PageNumber){
        UserFacade uf=new UserFacade();
        return uf.GetPage(10,PageNumber,EmailsToshow);
    }
    public ArrayList<Contact>getContactsPage(int PageNumber){
        UserFacade uf=new UserFacade(u);
        return uf.contactsPage(10,PageNumber,ContactsToshow);
    }
    public Contact SearchContact(String contactName){
        UserFacade uf=new UserFacade(u);
        return uf.SearchContact(contactName);
    }
    public int getEmailsNumberOfPages(){
        if(EmailsToshow==null){return 0;}
        int x=0;
        if(this.ContactsToshow.size()%10>0){x=1;}
        return this.EmailsToshow.size()/10+x;}
    public int getContactsNumberOfPages(){
        if(ContactsToshow==null){return 0;}
        int x=0;
        if(this.ContactsToshow.size()%10>0){x=1;}
        return this.ContactsToshow.size()/10+x;
    }

    public void keepMeLogIn(){
        UserFacade uf = new UserFacade(u);
        uf.keepMeLogIn();
    }

    public String logout(){
        this.u=null;
        return "logged out successfully";
    }

    public String bulkDelete(int id, int indexOfDefaultFolder){
        UserFacade uf = new UserFacade(u);
        return uf.bulkDelete(id, indexOfDefaultFolder);
    }

    public void bulkMove(ArrayList<Integer> eListIds, int indexOfDefaultFolder, int indexOfUserFolder){
        UserFacade uf = new UserFacade(u);
        uf.bulkMove(eListIds, indexOfDefaultFolder, indexOfUserFolder);
    }

    public String move(int id, int indexOfDefaultFolder, int indexOfUserFolder){
        UserFacade uf = new UserFacade(u);
        return uf.move(id, indexOfDefaultFolder, indexOfUserFolder);
    }

    public void bulkRetriveFromTrash(ArrayList<Integer> eListIds){
        UserFacade uf = new UserFacade(u);
        uf.bulkRetriveFromTrash(eListIds);
    }

    public String retriveFromTrash(int id){
        UserFacade uf = new UserFacade(u);
        return uf.retriveFromTrash(id);
    }

    public void fromDraftResendEmail(Email newEmail, int id, boolean send){
        UserFacade uf = new UserFacade(u);
        uf.fromDraftResendEmail(newEmail, id, send);
    }

    public void addToPriorityInbox(int id){
        UserFacade uf = new UserFacade(u);
        uf.addToPriorityInbox(id);
    }


    public void createUserFolder(String name){
        WithFiles.createFolder(name, u);
    }




    public static void main (String[] args) {
        Services s=new Services();
        System.out.println(s.SignUp("mohamed@gmail.com","123","mohamed"));
        System.out.println(s.SignUp("radwan@gmail.com","123","radwan"));
        System.out.println(s.SignUp("kamal@gmail.com","123","kamal"));
        System.out.println(s.SignUp("may@gmail.com","123","may"));
        System.out.println(s.login("mohamed@gmail.com","123"));
        s.getUser();
        ArrayList<File>attachs=new ArrayList<>();
        Email e=new Email();

        e.setImportance(Importance.veryImportant);
        e.setSender("mohamed@gmail.com");
        ArrayList<String>receivers=new ArrayList<String>();
        receivers.add("radwan@gmail.com");
        receivers.add("kamal@gmail.com");
        receivers.add("may@dgmail.com");
        e.setRecievers(receivers);
        e.setSubject("hello man");
        e.setBody("مش عايز قلق هنا");
        System.out.println(s.Compose(e,true));
        ArrayList<Email> emails=s.SetEmailsToShow(1);
        emails=s.getEmailsPage(1);
        for(int i=0;i<emails.size();i++){
            System.out.println(emails.get(i).getSubject()+emails.get(i).getId());
        }
        //System.out.println(s.removeContact("mohammed"));
        //s.getUser();
        ArrayList<Contact>contacts=s.getContactsPage(1);
        for(int i=0;i<contacts.size();i++){
            System.out.println(contacts.get(i).getName());
        }
        ArrayList<Integer>l=new ArrayList<>();
        l.add(66785136);
        s.addToPriorityInbox(66785136);
        contacts=s.getContactsPage(1);
        for(int i=0;i<contacts.size();i++){
            System.out.println(contacts.get(i).getName());
        }





    }



}
