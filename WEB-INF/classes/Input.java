import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

public class Input implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String ctype;
	String username;
	String password;

	Input(String ctype, String username, String password) {
		this.ctype = ctype;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "ctype:" + ctype + "\nusername: " + username + "\npassword: " + password;
	}
}
