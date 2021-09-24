package pojo;

public class Login {
	private String uname;
	private char[] pswd;
	public Login() {
		uname = new String();
	}
	public Login(String uname, char[] pswd) {
		super();
		this.uname = uname;
		this.pswd = pswd;
	}
	public String getUname() {
		return uname;
	}
	public char[] getPswd() {
		return pswd;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setPswd(char[] pswd) {
		this.pswd = pswd;
	}
}
