package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserFcade;


import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.EmailProxy;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.EmailSenderProxy;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.PageAdapter;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.PagingSystem;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.*;

import java.io.File;
import java.util.ArrayList;

public class UserFacade {
	 public User u;
	public UserFacade(){
		u = WithFiles.getKeepMe();
	}
	public UserFacade(User u) {
		this.u = u;
	}
	
	
	// here we add all the functionalities that user wants to do
	
	
	 /*Contact c = createContact(emailAddress, name);
		
		ContactToUserProxy cx = new ContactToUserProxy(u);
		cx.addContact(c);
		*/
	
	public ArrayList<String> composeEmail(Email e, boolean send) {
		ArrayList<String>steps=new ArrayList<>();
		if((this.u==null)||(e==null)){steps.add("Failed to send Email");
		return steps;}
		else {
			EmailSenderProxy ex = new EmailSenderProxy();
			ex.addSubject(e.getSubject());
			ex.addBody(e.getBody());
			ex.SetDate(e.getDate());
			ex.SetImportance(e.getImportance());
			for (int i = 0; i < e.getAttachements().size(); i++)
				ex.addAttachement(e.getAttachements().get(i));

			steps=ex.send(e,this.u.getEmailAddress(), e.getRecievers(), send);
			this.u=WithFiles.readUser(this.u.getEmailAddress());
			return steps;
		}
	}
	public String signUp(String emailAddress, String passWord,String username) {
		UserBuilder userBuilder = new UserBuilder();
		
		userBuilder.addEmailAddress(emailAddress);
		userBuilder.addPass(passWord);
		userBuilder.addUserName(username);
		return userBuilder.getUser();
	}
	
	public String logIn(String emailAddress, String passWord) {
		User newUser = WithFiles.readUser(emailAddress);
		if(newUser==null){
			return "This Email Address not Exist";
		}
		else {
			 if (newUser.getPassWord().equals(passWord)){
				u = newUser;
			 return "Successfully Log in";}
			else{
				return "Wrong password";}
			//here we may need to add a loop and reread password and start retreving email
		}
	}

	public String addContact(Contact c){
		if(this.u!=null){
		ContactToUserProxy ContactProxy=new ContactToUserProxy(this.u);
		String inform=ContactProxy.addContact(c);
		return inform;
		}
		else {return "UserAccount Undefined";}
	}
	public String editContact(Contact c,String oldName){
		if(this.u!=null){
			ContactToUserProxy ContactProxy=new ContactToUserProxy(this.u);
			String inform=ContactProxy.editContact(c,oldName);
			this.u=WithFiles.readUser(u.getEmailAddress());
			return inform;
		}
		else return "UserAccount Undefined";
	}
	public String removeContact(String contactName){
		if (this.u!=null){
			ContactToUserProxy ContactProxy=new ContactToUserProxy(this.u);
			String inform=ContactProxy.removeContact(contactName);
			this.u=WithFiles.readUser(u.getEmailAddress());
			return inform;
		}
		else return "UserAccount Undefined";
	}
	public  ArrayList<Email>SetEmailsToShow(User u,int EmailsInd){
		if(u==null){
			System.out.println("UserUndefined");
			return null;}
		PagingSystem p=new PagingSystem(u,EmailsInd);
		return p.getEmails();
	}
	public ArrayList<Email>FilterAndSorts(User u,int EmailsInd,ArrayList<ArrayList<String>> Filters,String sort){
		if(u==null){
			System.out.println("UserUndefined");
			return null;}
		PagingSystem p=new PagingSystem(u,EmailsInd);
		p.setEmails(p.Filter(Filters));
		p.Sorts(sort);
		return p.getEmails();
	}
	public ArrayList<Email>GetPage(int countOfEmails, int pageNumber, ArrayList<Email>ShowingEmails){
		if(ShowingEmails==null){
			System.out.println("UserUndefined");
			return null;}
		PageAdapter p=new PageAdapter();
		return 	p.getPage(countOfEmails,pageNumber,ShowingEmails);
	}
	public Contact SearchContact(String name){
		if(this.u==null){
			System.out.println("UserUndefined");
			return null;}
		ContactToUserProxy e=new ContactToUserProxy(this.u);
		return e.SearchContact(name);
	}
	public ArrayList<Contact>userContacts(){
		if(u==null){ System.out.println("UserUndefined");return null;};
		return u.getContacts();}
	public ArrayList<Contact>contactsPage(int countofContacs,int pagenumber,ArrayList<Contact>ShowingContacts){
		if(ShowingContacts==null){
			System.out.println("UserUndefined");
			return null;}
		PageAdapter p=new PageAdapter();
		return p.getContactsPage(countofContacs,pagenumber,ShowingContacts);
	}

	public void keepMeLogIn() {
		WithFiles.keepMeLogIn(u);
	}

	public void logOut(){
		WithFiles.keepMeLogOut();
	}


	public void bulkMove(ArrayList<Integer> eListIds, int indexOfDefaultFolder, int indexOfUserFolder) {
		EmailProxy epx = new EmailProxy(u);


		for (int i = 0; i < eListIds.size(); i++)
			epx.moveEmail(indexOfDefaultFolder, indexOfUserFolder, eListIds.get(i));

		WithFiles.writeUser(u);
	}


	public String move(int id, int indexOfDefaultFolder, int indexOfUserFolder) {
		EmailProxy epx = new EmailProxy(u);


		String word = epx.moveEmail(indexOfDefaultFolder, indexOfUserFolder, id);

		WithFiles.writeUser(u);
		return word;
	}

	public String bulkDelete(int id, int indexOfDefaultFolder) {
		EmailProxy epx = new EmailProxy(u);

		String word = epx.deleteEmail(id, indexOfDefaultFolder);

		WithFiles.writeUser(u);
		return word;
	}

	public void bulkRetriveFromTrash(ArrayList<Integer> eListIds){
		EmailProxy epx = new EmailProxy(u);

		for (int i = 0; i < eListIds.size(); i++)
			epx.retriveFromTrash(eListIds.get(i));

		WithFiles.writeUser(u);
	}
	public String retriveFromTrash(int id){
		EmailProxy epx = new EmailProxy(u);

		//for (int i = 0; i < eListIds.size(); i++)
		String word = epx.retriveFromTrash(id);

		WithFiles.writeUser(u);
		return word;
	}


	public void fromDraftResendEmail(Email newEmail, int id, boolean send) {
		EmailProxy epx = new EmailProxy(u);

		epx.fromDraftResendEmail(newEmail, id, send);

		WithFiles.writeUser(u);
	}

	public void addToPriorityInbox(int id){
		EmailProxy epx = new EmailProxy(u);
		epx.addToPriorityInbox(id);

		WithFiles.writeUser(u);
	}
}
