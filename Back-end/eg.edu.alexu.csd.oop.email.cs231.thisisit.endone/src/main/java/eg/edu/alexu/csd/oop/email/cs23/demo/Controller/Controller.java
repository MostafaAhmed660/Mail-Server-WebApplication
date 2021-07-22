package eg.edu.alexu.csd.oop.email.cs23.demo.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.Contact;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;
import eg.edu.alexu.csd.oop.email.cs23.demo.Services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("api")
@RestController
public class Controller {

    private Services services;
    @Autowired
    public Controller(Services services) {
        this.services = services;
    }
    @GetMapping("/login")
    public String Login(@RequestParam(value = "Emailaddress")String Emailaddress,
                      @RequestParam(value = "password")String password)
    { System.out.println("Name");
        String access=this.services.login(Emailaddress,password);
        this.services.getUser();
        return access;
    }
    @GetMapping("/getUser")
    public String getUser(){
        return this.services.getUser();
    }

    @GetMapping("/getUseraddress")
    public String getUseraddress(){
        return this.services.getUseraddress();
    }


    @GetMapping("/signUp")
    public String SignUp(@RequestParam(value = "emailaddress")String emailaddress,
                        @RequestParam(value = "password")String password,
                        @RequestParam(value = "userName")String userName)
    { return this.services.SignUp(emailaddress,password,userName);}

    @PostMapping("/addContact")
    public String addContact(@RequestBody Contact contact)
    {
        return this.services.addContact(contact);
    }

    @PostMapping("/editContact")
    public String editContact(@RequestBody Contact contact,
                              @RequestParam(value = "oldName")String oldName)
    {
        return this.services.editContact(contact,oldName);
    }
    @GetMapping("/removeContact")
    public String removeContact(@RequestParam(value = "Name")String Name)
    {
        return this.services.removeContact(Name);
    }
    @PostMapping("/SearchContact")
    public Contact SearchContact(@RequestParam(value = "Name")String Name)
    {
        return this.services.SearchContact(Name);
    }
    @GetMapping("/SetEmailsToShow")
    public ArrayList<Email> SetEmailsToShow(@RequestParam(value = "FolderIndex")int FolderIndex)
    {
        return this.services.SetEmailsToShow(FolderIndex);
    }
    @GetMapping("/getPage")
    public ArrayList<Email> getPage(@RequestParam(value = "PageNumber")int PageNumber)
    {
        return this.services.getEmailsPage(PageNumber);
    }
    @GetMapping("/GetContactPage")
    public ArrayList<Contact> GetContactPage(@RequestParam(value = "PageNumber")int PageNumber)
    {
        return this.services.getContactsPage(PageNumber);
    }
    @PostMapping(value = "/FilterAndSort")
    public ArrayList<Email> FilterAndSort(@RequestBody ArrayList<ArrayList<String>> Filter,
                                          @RequestParam(value = "sort")String  sort){
        System.out.println("hhhhh");
        return this.services.FilterAndSorts(Filter,sort);
    }
    @GetMapping(value = "/EmailsNumberOfPages")
    public int EmailsNumberOfpages(){
        return this.services.getEmailsNumberOfPages();
    }

    @GetMapping(value = "/ContactsNumberOfPages")
    public int ContactsNumberOfpages(){
        return this.services.getContactsNumberOfPages();
    }
    @PostMapping(value = "/ComposeMail")
    public ArrayList<String> sendMail(@RequestBody Email e, @RequestParam (value = "send")boolean send){
        return this.services.Compose(e,send);
    }
    @GetMapping("/logout")
    public String Logout() { return this.services.logout();
    }


    @GetMapping("/keepMe")
    public void keepMe(){
        services.keepMeLogIn();
    }




    @GetMapping("/bulkDelete")
    public String bulkDelete(@RequestParam(value = "id") int id, @RequestParam (value = "indexOfDefaultFolder")int indexOfDefaultFolder){
        return services.bulkDelete(id, indexOfDefaultFolder);
    }

    @PostMapping("/bulkMove")
    public void bulkMove(@RequestBody ArrayList<Integer> eListIds, @RequestParam (value = "indexOfDefaultFolder")int indexOfDefaultFolder, @RequestParam (value = "indexOfUserFolder") int indexOfUserFolder){
        services.bulkMove(eListIds, indexOfDefaultFolder, indexOfUserFolder);
    }

    @PostMapping("/move")
    public void bulkMove(@RequestParam (value = "id") int id, @RequestParam (value = "indexOfDefaultFolder")int indexOfDefaultFolder, @RequestParam (value = "indexOfUserFolder") int indexOfUserFolder){
        services.move(id, indexOfDefaultFolder, indexOfUserFolder);
    }


    @GetMapping("/retriveFromTrash")
    public String retriveFromTrash(@RequestParam(value = "id") int id){
        return services.retriveFromTrash(id);
    }

    @PostMapping("/fromDraftResendEmail")
    public void fromDraftResendEmail(@RequestBody Email newEmail,  @RequestParam (value = "id")int id,  @RequestParam (value = "send")boolean send){
        services.fromDraftResendEmail(newEmail, id, send);
    }

    @GetMapping("/addToPriorityInbox")
    public void addToPriorityInbox( @RequestParam (value = "id")int id){
        services.addToPriorityInbox(id);
    }


















}
