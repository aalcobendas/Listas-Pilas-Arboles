package Fase2;

public class User {
	
	public String login;
	public String fullName;
	public Integer age;
	public String gender;
	public Integer complaints;
	
	
	public User(String login, String fullName, Integer age, String gender, Integer complaints) {
		this.login=login;
		this.fullName=fullName;
		this.age=age;
		this.gender=gender;
		this.complaints=complaints;
	}
	
	public User(String login, String fullName, Integer age, String gender) {
		this.login=login;
		this.fullName=fullName;
		this.age=age;
		this.gender=gender;
		this.complaints=0;
	}
	
	public User(){
		
	}
	
	public boolean equals(User obj) {
        boolean result=false;
        if (obj!=null) {
                result=this.login.equalsIgnoreCase(obj.login) && 
                this.fullName.equalsIgnoreCase(obj.fullName) &&
                this.age==obj.age &&
                this.gender.equals(obj.gender) &&
                this.complaints==obj.complaints;
        }
        return result;
        
    }
	
	public String toString(){
		String result="";
		result+="Login: " + this.login + ". Full Name: " + this.fullName + ". Age: " + this.age + ". Gender: " + this.gender
				+ ". Complaints: " + this.complaints;
		return result;
	}

}
