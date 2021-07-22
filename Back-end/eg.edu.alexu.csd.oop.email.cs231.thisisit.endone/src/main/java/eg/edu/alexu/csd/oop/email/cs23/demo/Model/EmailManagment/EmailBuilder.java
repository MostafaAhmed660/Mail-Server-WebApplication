package eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.io.File;

public class EmailBuilder {
	private Email e;
	
	
	
	public EmailBuilder() {
		e = new Email();
	}
	public EmailBuilder(Email e){this.e = e;}
	public void setId(int id) {
		e.setId(id);
	}
	public void setSender(String sender) {
		e.setSender(sender);
	}
	public void setReceiver(String receiver){e.setReceiver(receiver);}
	public void addReciever(String reciever) {
		e.addReciever(reciever);
	}
	public void setDate(String date) {
		e.setDate(date);
	}
	public void setImportance(Importance importance) {
		e.setImportance(importance);
	}
	public void setSubject(String subject) {
		e.setSubject(subject);
	}
	public void setBody(String body) {
		e.setBody(body);
	}
	public void addAttachement(File attachement) {
		e.addAttachement(attachement);
	}
	public Email getEmail() {
		if(e.getSubject()==null||e.getRecievers().size()==0/*||e.getDate()==null*/){
			return null;
		}
		return e;
	}
	
	
	
	
}
