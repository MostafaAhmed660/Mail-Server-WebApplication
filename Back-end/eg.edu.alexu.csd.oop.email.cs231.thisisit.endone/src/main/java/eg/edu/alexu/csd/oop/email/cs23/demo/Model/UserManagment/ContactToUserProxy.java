package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment;


import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;

import java.util.ArrayList;

public class ContactToUserProxy {

	private User u;
	
	public ContactToUserProxy(User u1) {
		u = u1;
	}
	
	public String addContact(Contact c) {
		ContactBuilder builder=new ContactBuilder();
		builder.SetContactName(c.getName());
		builder.addEmailaddress(c.getEmailAddresses());
		c=builder.getContact();
		if(c==null){return "Failed to add this Contact,Check Contact Name or Email Addresses may not exist";}
		for(int i=0;i<u.getContacts().size();i++){
			if(u.getContacts().get(i).getName().equals(c.getName())){
				return "Contact already exist";
			}
		}
		u.addToContacts(c);
		WithFiles.writeUser(u);
		u=WithFiles.readUser(u.getEmailAddress());
		 return "Successfully added contact";
	}
	
	public String removeContact(String ContactName) {
		if(u.deleteContact(ContactName)){
			WithFiles.writeUser(u);
			u=WithFiles.readUser(u.getEmailAddress());
			return "Contact Successfully Deleted";
		}
		else return "Contact Already Not Exist";
	}
	public String editContact(Contact c, String oldName)
	{	ContactBuilder builder=new ContactBuilder();
		builder.SetContactName(c.getName());
		builder.addEmailaddress(c.getEmailAddresses());
		c=builder.getContact();
		if(c==null){return "Failed to edit this Contact,Check Contact Name or Email Addresses may not exist";}
	else {
			for (int i = 0; i < u.getContacts().size(); i++) {
				if (u.getContacts().get(i).getName().equals(oldName)) {
					if(!oldName.equals(c.getName())){
				for (int j = 0; j < u.getContacts().size(); j++) {
					if (u.getContacts().get(j).getName().equals(c.getName())){
						return "Failed Editing this Contact!..There's another Contact with the same Name";
					}
				}
					}
				u.getContacts().set(i,c);
				WithFiles.writeUser(u);
				u=WithFiles.readUser(u.getEmailAddress());
				return "Contact edited Successfully";}
			}
			return "Contact Not Found to be Edited";
		}
	}
	public ArrayList<Contact>GetContacts(){
		return this.u.getContacts();
	}
	public Contact SearchContact(String name) {
		for (int i = 0; i < this.u.getContacts().size(); i++) {
			if (this.u.getContacts().get(i).getName().equals(name)) {
				return this.u.getContacts().get(i);
			}
		}
		return null;
	}
	public static void main(String[] args) {
		UserBuilder u1 = new UserBuilder();
		u1.addUserName("ahmed");
		u1.addEmailAddress("here@here.com");
		u1.addPass("123");
		u1.getUser();
		User user1 = WithFiles.readUser("here@here.com");
		UserBuilder u2 = new UserBuilder();
		u2.addUserName("mohamedd");
		u2.addEmailAddress("radwan@here.com");
		u2.addPass("123");
		u2.getUser();
		User user2 = WithFiles.readUser("radwan@here.com");
		UserBuilder u3 = new UserBuilder();
		u3.addUserName("kamal");
		u3.addEmailAddress("kamal@here.com");
		u3.addPass("123");
		u3.getUser();
		User user3 = WithFiles.readUser("kamal@here.com");
		ContactBuilder builder = new ContactBuilder();
		builder.SetContactName("Mohamed Radwan");
		ArrayList<String> a=new ArrayList<String>();
		a.add("radwan@here.com");
		a.add("kamal@here.com");
		builder.addEmailaddress(a);
		Contact c=builder.getContact();
		ContactToUserProxy proxy=new ContactToUserProxy(user1);
		String s=proxy.addContact(c);
		System.out.println(s);
		for(int i=0;i<user1.getContacts().size();i++){
			System.out.println(user1.getContacts().get(i).getName());
			System.out.println(user1.getContacts().get(i).getEmailAddresses());
		}

		/*User u2 = new User();
		u2.setEmailAddress("there@there.com");
		Contact c2 = new Contact();
		c2.addToEmailAddresses("there@there.com");
		c2.addToEmailAddresses("ok@ok.com");
		c2.addToOriginalUser(u2);
		
		
		User u3 = new User();
		u3.setEmailAddress("care@care.com");
		Contact c3 = new Contact();
		c3.addToEmailAddresses("care@care.com");
		c3.addToOriginalUser(u3);
		
		User u4 = new User();
	/*	u4.addToContacts(c1);
		u4.addToContacts(c2);
		u4.addToContacts(c3);
		*/
		/*ContactToUserProxy cx = new ContactToUserProxy(u4);
		
		cx.addContact(c1);
		cx.addContact(c2);
		cx.addContact(c3);
		 
		System.out.println(u4.getContacts());
		
		cx.editContact(c1, "care@care.com");
	//	u4.editContact(c1, "care@care.com");
		System.out.println(u4.getContacts());
		cx.removeContact("care@care.com");
		System.out.println(u4.getContacts());

		/*u4.deleteContact("care@care.com");
		
		System.out.println(u4.getContacts());

		
		u4.deleteContact("ok@ok.com");
		
		System.out.println(u4.getContacts());
		
		u4.deleteContact("here@here.com");
		
		System.out.println(u4.getContacts());
		*/
		
	}
	


}
