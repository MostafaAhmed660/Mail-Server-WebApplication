package eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserFcade;



import eg.edu.alexu.csd.oop.email.cs23.demo.Model.EmailManagment.Email;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.HelperClasses.WithFiles;
import eg.edu.alexu.csd.oop.email.cs23.demo.Model.UserManagment.User;

import java.io.File;
import java.util.ArrayList;

public class MainTest {
	
	public static void main (String[] args) {
	//	UserFacade uf = new UserFacade();
		
		
		/*User u2 = new User();
		u2.setEmailAddress("454s6sdff@dsfhkf.com");
		u2.setPassWord("sl4fd5sf");
		WithFiles.initUser(u2);
	*/

		//uf.signUp("454s6sdff@dsfhkf.com", "sl4fd5sf");
		
		//uf.signUp("now@465.com", "5656fs");
		
		/*UserBuilder ub1 = new UserBuilder();
		ub1.addEmailAddress("on@on.com");
		ub1.addPass("online");
		User u1 = ub1.getUser();
		
		
		UserBuilder ub2 = new UserBuilder();
		ub2.addEmailAddress("off@off.com");
		ub2.addPass("offline");
		User u2 = ub2.getUser();
		
		
		
		File f1 = new File("E:\\Quran\\الشيخ عبدالرحمن الزواوي\\12-يوسف\\سورة يوسف.mp4");
		File f2 = new File("E:\\fall 2021\\first term\\numericals\\Numerical-Session 3 N.pdf");
		
		ArrayList<File> attachs = new ArrayList<File>();

		attachs.add(f1);
		attachs.add(f2);
		
		EmailSenderProxy ex = new EmailSenderProxy();
		u2 = null;
		u2 = WithFiles.readUser("off@off.com");
		
		ex.addAttachement(f1);
		ex.addAttachement(f2);
		ex.addBody("here is trying");
		ex.addSubject("subj");
		ex.send(u1, u2, true);
		
		//uf.u = u1;
		//uf.composeEmail(u2.getEmailAddress(), "off", attachs, "this is our email", "subj", true);
		
		ArrayList<Email> it = u1.getDefaultFolders().get(4).getEmailsList();
		
		
		System.out.println(it);
		
		ArrayList<Email> itt = u2.getDefaultFolders().get(0).getEmailsList();
		
		
		System.out.println(itt);
		
		
		*/
		UserFacade uf1 = new UserFacade();
		
		
	/*	uf1.signUp("off@off.com", "05");
		
		
		
		User u2 = WithFiles.readUser("do@do");
		*/
		
		System.out.println(uf1.signUp("vo@do", "65","Mohamed"));
		System.out.println(uf1.signUp("uo@do","46","ahmed"));
		uf1.u=WithFiles.readUser("vo@do");
		
		
		//File f1 = new File("E:\\Quran\\الشيخ عبدالرحمن الزواوي\\12-يوسف\\سورة يوسف.mp4");
		//File f2 = new File("E:\\fall 2021\\first term\\numericals\\Numerical-Session 3 N.pdf");
		
		ArrayList<File> attachs = new ArrayList<File>();

		//attachs.add(f1);
		//attachs.add(f2);
		
		

		/*uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "lest1", true);
		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "best2", true);

		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "lest3", true);
		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "best4", true);

		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "lest5", true);
		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "best6", true);

		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "lest7", true);
		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "best8", true);

		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "lest9", true);
		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "best10", true);

		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "lest11", true);
		uf1.composeEmail("uo@do", "dfk", attachs, "here is that", "best12", true);
*/
		ArrayList<ArrayList<String>>filter=new ArrayList<ArrayList<String>>(0);
		ArrayList<String>l=new ArrayList<String>(0);
		filter.add(l);
		ArrayList<Email>it=uf1.FilterAndSorts(uf1.u,4,filter,"subject");
		//it=uf1.GetPage(5,3,it);
		for (int i = 0; i < it.size(); i++)
			((Email)it.get(i)).print();
		
		System.out.println(it);
		



	}

}
