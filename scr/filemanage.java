package eg.edu.alexu.csd.datastructure;

import java.io.*;

public class filemanage {

        //read from file
        public static String read(File file) throws Exception{
            FileReader f = new FileReader(file);
            int i ;
            String s = "" ;
            while ((i=f.read())!=-1){
                s += (char)i;
            }
            f.close();
            return s ;
        }

        //write string s into file
        public static void write(File file,String s) throws Exception{
            FileWriter f = new FileWriter(file);
            f.write(s);
            f.flush();
            f.close();
        }

        //write Object into file
        public static void writeObject(File file,Object ac) throws Exception{
            FileOutputStream f1 = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f1);
            o.writeObject(ac);
            o.flush();
            o.close();   f1.close();
        }

        //read object from file
        public static Object readObject(File file) throws Exception{
            FileInputStream f1 = new FileInputStream(file);
            ObjectInputStream o = new ObjectInputStream(f1);
            Object ac = (Object)o.readObject();
            //System.out.print("email :"+ac.email+"\nphone numer :"+ac.phonenumber+"\nusername :"+ac.username);
            o.close();   f1.close();
            return ac ;
        }

        //read from file1 and write into file2
        public static void readwrite(File file1,File file2) throws Exception {
            String s = read(file1);
            write(file2,s);
        }

        //write string s into three files
        public static void writeinto(File file1, File file2,File file3,String s) throws Exception {
                write(file1,s);
                write(file2,s);
                write(file3,s);
        }

        //read from two files
        public static String readfrom(File file1, File file2) throws Exception {
            String s = read(file1) + read(file2);
            return s ;
        }

        //read from two files and write in file3
        public static void readfromtwointothree(File file1, File file2, File file3) throws Exception {
            String s = read(file1) + read(file2);
            write(file3,s);
        }

}
