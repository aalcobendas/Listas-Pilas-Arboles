package Fase2;


public interface IBSTree {

	public int getSize();
	public int getHeight();
	public void showPreOrder();
	public void showPostOrder();
	public void showInOrder();
	public void showLevel();
	public void insertUser(User user);
	public void removeUser(String key);
	public User findUser(String key);
	
}
