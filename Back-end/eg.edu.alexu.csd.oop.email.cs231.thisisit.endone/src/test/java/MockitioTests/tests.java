package MockitioTests;


import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.Contact;
import eg.edu.alexu.csd.oop.email.cs23.demo.Services.Services;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static reactor.core.publisher.Mono.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class tests {


    @Mock
    private Services services;

    @Autowired
    public tests() {
        this.services = new Services();
    }


    @Test
    public void signUpTest(){
       services = new Services();
        //when(services.SignUp("Gomaawy@gmail.com", "sdjhhhfk", "Gomaa12")).thenReturn("Email Sent Successfully");

        Assertions.assertEquals("Successfully created" ,services.SignUp("Gomaaawy@gmail.com", "sdjhhhfk", "Gomaa12"));
        Assertions.assertEquals("There's another account with tha same ID" ,services.SignUp("Gomaaawy@gmail.com", "sdjhhhfk", "Gomaa12"));
    }


    @Test
    public void logInTest(){
        services = new Services();
        Assertions.assertEquals("Successfully Log in" ,services.login("Gomaaawy@gmail.com", "sdjhhhfk"));
        Assertions.assertEquals("Wrong password" ,services.login("Gomaaawy@gmail.com", "sdjhdfjkhsdkfjhhhfk"));
        Assertions.assertEquals("This Email Address not Exist" ,services.login("Gomaaafjkhsdkjfhsdkwy@gmail.com", "sdjhdfjkhsdkfjhhhfk"));
    }

    @Test
    public void ComposeTest(){
        services = new Services();
        services.login("Gomaaawy@gmail.com", "sdjhhhfk");
        Email e = new Email();
        e.setId(18);
        e.setSender("Gomaaawy@gmail.com");
        ArrayList<String> list = new ArrayList<>();
        list.add("radwan@here.com");
        e.setRecievers(list);
        Assertions.assertEquals("Failed to send Email" ,services.Compose(null, true));
        Assertions.assertEquals( "Email Drafted" ,services.Compose(e, false));
        Assertions.assertEquals("Email Sent Successfully",services.Compose(e, true));
    }

    @Test
    public void addContactTest(){
        services = new Services();
        services.login("Gomaaawy@gmail.com", "sdjhhhfk");
        Contact c = new Contact();
        c.setName("fhds");
        ArrayList<String> list = new ArrayList<>();
        list.add("radwan@here.com");
        c.setEmailAddresses(list);
        //Assertions.assertEquals("Successfully added contact", services.addContact(c));
        Assertions.assertEquals("Contact already exist", services.addContact(c));
    }


    @Test
    public void editContactTest() {
        services = new Services();
        services.login("Gomaaawy@gmail.com", "sdjhhhfk");
        Contact c = new Contact();
        c.setName("fhds");
        ArrayList<String> list = new ArrayList<>();
        list.add("radwan@here.com");
        c.setEmailAddresses(list);

        Assertions.assertEquals("Failed Editing this Contact!..There's another Contact with the same Name", services.editContact(c, "fhds"));

    }

    @Test
    public void deleteContactTest() {
        services = new Services();
        services.login("Gomaaawy@gmail.com", "sdjhhhfk");
        Contact c = new Contact();
        c.setName("fhds");
        ArrayList<String> list = new ArrayList<>();
        list.add("radwan@here.com");
        c.setEmailAddresses(list);

        Assertions.assertEquals("Contact Successfully Deleted", services.removeContact("fhds"));
    }





    @Test
    public void getPageTest(){
        services = new Services();
        services.login("Gomaaawy@gmail.com", "sdjhhhfk");
        System.out.println(services.getEmailsPage(0));

    }

    @Test
    public void getContactTest(){
        services = new Services();
        services.login("Gomaaawy@gmail.com", "sdjhhhfk");
        System.out.println(services.getEmailsPage(0));

    }

    @Test
    public void filterAndSort(){
        services = new Services();
        services.login("Gomaaawy@gmail.com", "sdjhhhfk");
        System.out.println(services.getEmailsPage(0));

    }


}
