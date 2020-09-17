package Fase2;

public class BSTNode{
	
	//ATRIBUTOS
	public String key;
	public User user;
	public BSTNode parent;
	public BSTNode leftChild;
	public BSTNode rightChild;
	
	//CONSTRUCTOR
	public BSTNode(User user){
		this.key=user.login;
		this.user=user;
	}
	
}

