package Fase1;

public class Request {
	
	public String source;  //origen
	public String target;  //destino
	public String login;   //usuario
	
	public Request(String login, String source, String target){
		this.source=source;
		this.target=target;
		this.login=login;
	}
	
	public String toString(){
		String mensaje="Login: " + this.login + ". Source: " + this.source + ". Target: " + this.target + ".";
		return mensaje;
	}
	
	public boolean equals(Request obj) { 
        boolean result=false;
  try {
      result= obj.login.equalsIgnoreCase(this.login) && 
        obj.source.equalsIgnoreCase(this.source) &&    obj.target.equalsIgnoreCase(this.target);
        } catch (Exception e) {
              //obj is null or some of the attributes are null
              result=false;
        }
        return result;
  }


}
