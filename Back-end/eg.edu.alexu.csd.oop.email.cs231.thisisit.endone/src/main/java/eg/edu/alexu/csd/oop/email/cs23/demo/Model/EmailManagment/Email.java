package eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.AttachementClass;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;
import lombok.With;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class Email  {

	private int id;
	private String sender;
	private ArrayList<String> recievers;
	private boolean seen;
	private String date;
	private Importance importance;

	private String subject;
	private String body;
	private ArrayList<File> attachements ;
	private String previousPositionForTrashedObj;


	public void setRecievers(ArrayList<String> recievers) {
		this.recievers = recievers;
	}



	public void setAttachements(ArrayList<File> attachements) {
		this.attachements = attachements;
	}


	public void setReceiver(String receiver){
		this.recievers.add(receiver);
		this.recievers.set(0,receiver);}

	
	public Email() {
		attachements  = new ArrayList<File>();
		recievers = new ArrayList<String>();

	}

	public String getPreviousPositionForTrashedObj() {
		return previousPositionForTrashedObj;
	}

	public void setPreviousPositionForTrashedObj(String previousPositionForTrashedObj) {
		this.previousPositionForTrashedObj = previousPositionForTrashedObj;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}

	
	
	
	public ArrayList<String> getRecievers() {
		return recievers;
	}

	public void addReciever(String reciever) {
		recievers.add(reciever);
	}

	public boolean isSeen() {
		return seen;
	}
	
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	

	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	public Importance getImportance() {
		return importance;
	}
	
	public void setImportance(Importance importance) {
		this.importance = importance;
	}
	
	
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
	public ArrayList<File> getAttachements() {
		return attachements;
	}
	
	public void addAttachement(File attachement) {
		this.attachements.add(attachement);
	}

	//protoTypeDB
	@Override
	public Email clone() {
		Email e = new Email();
		e.setBody(this.getBody());
		e.setDate(this.date);
		//WithFiles.addIdToEmail(e);//new id
		e.setSeen(this.seen);
		e.setSubject(this.subject);
		e.setImportance(this.importance);
		e.setRecievers(this.recievers);
		e.setSender(this.sender);
		e.setAttachements(this.attachements);
		e.setId(this.id);

		return e;
	}
	
	
	
	public void print() {
		System.out.println(subject);
		System.out.println("........................");
		System.out.println(body);
		
		ArrayList<File> arr = getAttachements();
		
		for (File f : arr)
			AttachementClass.viewAttach(f);
	}
}
