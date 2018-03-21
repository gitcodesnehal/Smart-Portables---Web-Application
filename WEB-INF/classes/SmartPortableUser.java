import java.io.*;
import java.util.*;
public class SmartPortableUser {
	 String fname;
	 String lname;
	 String uid;
	 String emailid;
	 String password;
	 String type;
	 //ArrayList<Order> orders = new ArrayList<Order>();
	
	 public SmartPortableUser(String fname,String lname, String uid, String emailid, String password, String type)
	 {
		 this.fname=fname;
		 this.lname=lname;
		 this.uid=uid;
		 this.emailid=emailid;
		 this.password=password;
		 this.type=type;
		 
	 }
	 
	 
	 
	 public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getEmailid() {
			return emailid;
		}

		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		
		
		
}
