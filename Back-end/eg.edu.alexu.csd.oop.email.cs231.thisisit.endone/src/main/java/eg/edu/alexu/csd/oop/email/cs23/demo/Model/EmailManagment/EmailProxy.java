package eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment;

import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.io.*;
import java.util.ArrayList;

public class EmailProxy {
	private User user;
	private int id;
	private String path ;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmailProxy(User u) {
		this.user = u;
	}

	public EmailProxy(User u, int id, String path) {
		this.user = u;
		this.id = id;
		this.path = path;
	}
	public User getU() {
		return user;
	}
	public void setU(User u) {
		this.user = u;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	/*public void deleteEmail() {
		String location = path.substring(path.indexOf("\\")+1);
		String User=path.substring(0,path.indexOf("\\"));
		File userFile = new File("users\\" + getPath()+ "\\emails.txt");
		ArrayList<Email> u = new ArrayList<Email>(0);
		try {
			FileInputStream fis = new FileInputStream(userFile);
			ObjectInputStream ois = new ObjectInputStream(fis);

			u = (ArrayList<Email>) ois.readObject();
		}
		catch(Exception e) {
			System.out.println("error in reading user's file");
			return;
		}
		//deleting
		int n = u.size();
		for (int i = 0 ; i < n ; i++){
			if (u.get(i).getId() == id){
				u.remove(i);
				break;
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(userFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(u);
			oos.flush();
			oos.close();
			fos.close();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		//this point we need to refresh the system
	}
*/

	//here we use it to move elements from one position to another
	public String moveEmail(int index, int secondIndex,int id) {
		//take care of index
		return user.MoveEmailWithoutdelete(1, index, 2, secondIndex, id);
	}

	public String deleteEmail(int id, int index){
		//here we delete to trash
		if (index == 2)
			return "cannot delete from Trash";
		else
			return user.MoveEmailWithdelete(1, index, 1, 2, id);
	}


	//for drafted objects
	//for resending it
	public void fromDraftResendEmail(Email newEmail, int id, boolean send) {
		ArrayList<Email> eList = user.getDefaultFolders().get(3).getEmailsList();
		Email e = null;
		//remove from draft
		for (int i = 0; i < eList.size(); i++)
			if (eList.get(i).getId() == id) {
				e = eList.remove(i);
				break;
			}

		//resend it
		EmailSenderProxy epx = new EmailSenderProxy(newEmail);
		epx.send(e,user.getEmailAddress(), newEmail.getRecievers(), send);

	}

	public String  retriveFromTrash(int id){
		ArrayList<Email> eList = user.getDefaultFolders().get(2).getEmailsList();
		Email e = null;
		//remove from draft
		for (int i = 0; i < eList.size(); i++)
			if (eList.get(i).getId() == id) {
				e = eList.remove(i);
				break;
			}
		if (e == null){
			return "Email not found";

		}
		//System.out.println(e.getPreviousPositionForTrashedObj());
		if (e.getPreviousPositionForTrashedObj().equals("defaultInbox"))
			user.getDefaultFolders().get(0).getEmailsList().add(e);
		else if (e.getPreviousPositionForTrashedObj().equals("sentEmails"))
			user.getDefaultFolders().get(4).getEmailsList().add(e);
		else if (e.getPreviousPositionForTrashedObj().equals("drafts"))
			user.getDefaultFolders().get(3).getEmailsList().add(e);

		return "Done";
	}

	//add out to keep the original one
	public void addToPriorityInbox(int id){
		user.MoveEmailWithdelete(1,0, 0,1, id);
	}



	public static void main(String[] args){


	}


}