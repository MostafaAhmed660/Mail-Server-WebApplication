package eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.ArrayListIterator;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.PageAdapter;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.Paging;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.Contact;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.Folder;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.io.File;
import java.util.ArrayList;

public class EmailSenderProxy implements EmailSender{
	private EmailBuilder builder;
	public EmailSenderProxy() {
		builder = new EmailBuilder();
	}
	public EmailSenderProxy(Email e) {
		builder = new EmailBuilder(e);
	}



	@Override
	public void addSubject(String subject) {
		builder.setSubject(subject);
	}

	
	@Override
	public void addBody(String body) {
		builder.setBody(body);
	}
	public void SetDate(String Date){
		builder.setDate(Date);
	}

	public void SetImportance(Importance i){
		builder.setImportance(i);
	}

	
	@Override
	public void addAttachement(File attachement) {
		builder.addAttachement(attachement);
	}
	
	@Override
	public ArrayList<String> send(Email email,String sender, ArrayList<String>reciever, boolean send) {
		ArrayList<String>Steps=new ArrayList<>();
		for(int i=0;i<reciever.size();i++) {
			User s = WithFiles.readUser(sender);
			User r = WithFiles.readUser(reciever.get(i));
			if(s==null){Steps.add("User Undefined");}
			if(r==null){
				Folder senderDraft = s.getDefaultFolders().get(3);
				senderDraft.addToEmailsList(email);
				WithFiles.writeUser(s);
				 Steps.add("this Receiver address:"+reciever.get(i)+" is not correct, message is drafted");}
			else {
			builder.setSender(sender);
			builder.setReceiver(r.getEmailAddress());
			Email e = builder.getEmail();

			//may update to add more conditions
			if (send) {
				if(e==null){
					Folder senderDraft = s.getDefaultFolders().get(3);
					WithFiles.addIdToEmail(email);
					senderDraft.addToEmailsList(email);
					WithFiles.writeUser(s);
					Steps.add("Message Drafted");
					}
				else {
					WithFiles.addIdToEmail(e);
				Folder recieverInbox = r.getDefaultFolders().get(0);
				//recieverInbox.setPath("users\\" + reciever.getEmailAddress() + "\\defaultInbox\\");
				recieverInbox.addToEmailsList(e);

				Folder senderSentEmail = s.getDefaultFolders().get(4);
				//senderSentEmail.setPath("users\\" + sender.getEmailAddress() + "\\sentEmails\\");
				senderSentEmail.addToEmailsList(e);
				WithFiles.writeUser(r);
				WithFiles.writeUser(s);
				Steps.add("Email Sent Successfully");
				}
			} else {
				Folder senderDraft = s.getDefaultFolders().get(3);
				senderDraft.addToEmailsList(e);
				WithFiles.writeUser(s);
				Steps.add("Email drafted");
			}
		}
		}
		//not essential as you will need to write this user after each update
		return Steps;
	}
	
	
	public static void main(String[] args) {

		User u1 = new User();
		u1.setEmailAddress("mostaf@...com");
		User u2 = new User();
		u2.setEmailAddress("killer@...com");
		WithFiles.initUser(u1);
		WithFiles.initUser(u2);

		Contact c1 = new Contact();
		

		u1.addToContacts(c1);

		
		EmailSenderProxy ex = new EmailSenderProxy();
		
		ex.addBody("here we are");
		ex.addSubject("test");
		//File f = new File("E:\\Quran\\الشيخ عبدالرحمن الزواوي\\12-يوسف\\سورة يوسف.mp4");
		//File f2 = new File("E:\\fall 2021\\first term\\numericals\\Numerical-Session 3 N.pdf");
		//ex.addAttachement(f2);
		//ex.addAttachement(f);
		
		/*ex.send(u1, u2, true);
		ex.send(u1, u2, true);
		ex.send(u1, u2, true);
		
		for (int i = 0; i < 3; i++)
			u2.getDefaultFolders().get(0).getEmailsList().get(i).print();
		
		u2.getDefaultFolders().get(0).getEmailsList().get(0).print();
		WithFiles.writeUser(u1);
		WithFiles.writeUser(u2);

		System.out.println(u1.getDefaultFolders().get(4).getEmailsList());
		u1.getDefaultFolders().get(4).getEmailsList().get(0).print();
		System.out.println(u2.getDefaultFolders().get(0).getEmailsList());
		
		Paging p = new PageAdapter(u1);
		
		ArrayListIterator it = p.getPage(3, 1, 4);
		System.out.println();
		
		while(it.hasNext())
			((Email) it.next()).print();
		System.out.println(u1.getDefaultFolders().get(0).getPath());*/
	}

	
	
	

}
