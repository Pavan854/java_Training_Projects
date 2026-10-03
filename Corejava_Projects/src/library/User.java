package library;

public class User {
	
	private int userId;
	private String name;
	
	User(int userId,String name){
		this.userId=userId;
		this.name=name;
		
	}
	
	public String toString() {
		return "UserID "+userId+" Name "+name;
	}
	
	public void setUserId(int userId) {
		
		this.userId=userId;
		
	}
	public int getUserId(){
		return userId;
	}
	public void setName(String name) {
		
		this.name=name;
		
	}
	public String getName(){
		return name;
	}
	
	

}
