package Fase2;

import java.util.LinkedList;
import java.util.Queue;




public class UsersClass implements IBSTree{

	public BSTNode root;

	public UsersClass(){

	}

	public UsersClass(BSTNode root){
		this.root=root;
	}
	
	public  DList getSortedList() {
		DList list=new DList();
		return getSortedList(this.root,list);
	}
	
	public static DList getSortedList(BSTNode node, DList list)  {
		if (node != null) {
			list=getSortedList(node.leftChild,list);
			list.addLast(node.key);
			list=getSortedList(node.rightChild,list);
		}
		return list;
	}

	public int getSize(){
		return getSize(root);
	}

	private int getSize(BSTNode node){
		if(node==null){
			return 0;
		}else{
			int result= 1 + getSize(node.leftChild) + getSize(node.rightChild);
			return result;
		}
	}

	public int getHeight(){
		return getHeight(root);
	}

	private int getHeight(BSTNode node){
		if(node==null){
			return 0;
		}else{
			int result=1+Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
			return result;
		}
	}

	public void showPreOrder(){
		showPreOrder(root);
	}

	private void showPreOrder(BSTNode node){
		if(node==null){
			return;
		}else{
			System.out.println(node.key);
			showPreOrder(node.leftChild);
			showPreOrder(node.rightChild);
		}

	}

	public void showPostOrder(){
		showPostOrder(root);
	}

	private void showPostOrder(BSTNode node){
		if(node==null){
			return;
		}else{
			showPostOrder(node.leftChild);
			showPostOrder(node.rightChild);
			System.out.println(node.key);
		}
	}

	public void showInOrder(){
		showInOrder(root);
	}

	private void showInOrder(BSTNode node){
		if(node==null){
			return;
		}else{
			showInOrder(node.leftChild);
			System.out.println(node.key);
			showInOrder(node.rightChild);
		}
	}

	public void showLevel(){

		Queue <BSTNode> queue = new LinkedList<BSTNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			BSTNode nodeIt = queue.poll();
			System.out.println(nodeIt.key + " ");

			if(nodeIt.leftChild!=null){
				queue.add(nodeIt.leftChild);
			}

			if(nodeIt.rightChild!=null){
				queue.add(nodeIt.rightChild);
			}	
		}
	}
	
	public void complaint(String key){
		complaint(root,key);
	}
	
	private void complaint(BSTNode node, String key){
		if(node==null){
			return;
		}
		if(node.key.equalsIgnoreCase(key)){
			node.user.complaints+=1;
			if(node.user.complaints==5){
				removeUser(key);
			}
		}
		complaint(node.leftChild,key);
		complaint(node.rightChild,key);
	}
	
	public DList[] extremeUsers(){
		return extremeUsers(root);		 
	}
	
	private DList[] extremeUsers(BSTNode node){
		Queue <BSTNode> queue = new LinkedList<BSTNode>();
		int max=root.user.complaints;
		int min=root.user.complaints;
		DList[] listas = new DList[2];
		listas[0]=new DList();  //almacena los maximos
		listas[1]= new DList();  //almacena los minimos
		
		//almacenamos valor maximo y valor minimo de complaints
		queue.add(root);
		while(!queue.isEmpty()){
			BSTNode nodeIt = queue.poll();
			if(nodeIt.user.complaints>=max){
				max=nodeIt.user.complaints;	
			}
			if(nodeIt.user.complaints<=min){
				min=nodeIt.user.complaints;
			}
			if(nodeIt.leftChild!=null){
				queue.add(nodeIt.leftChild);
			}
			if(nodeIt.rightChild!=null){
				queue.add(nodeIt.rightChild);
			}
		}
		
		//insertamos en las listas los usuarios con mas o menos complaints
		queue.add(root);
		while(!queue.isEmpty()){
			BSTNode nodeIt = queue.poll();
			if(nodeIt.user.complaints==max){
				listas[0].addLast(nodeIt.user.login);
			}
			if(nodeIt.user.complaints<=min){
				listas[1].addLast(nodeIt.user.login);	
			}
			if(nodeIt.leftChild!=null){
				queue.add(nodeIt.leftChild);
			}
			if(nodeIt.rightChild!=null){
				queue.add(nodeIt.rightChild);
			}
		}
		return listas;
	}

	public void show(){
		show(root);
	}
	
	private void show(BSTNode node){
		if(node==null){
			return;
		}
		show(node.rightChild);
		System.out.println(node.key);
		show(node.leftChild);
	}
	
	public void insertUser(User user){
		BSTNode newNode = new BSTNode(user);
		if(root==null){
			root=newNode;
		}else{
			insert(newNode,root);
		}
	}
	
	private void insert(BSTNode newNode, BSTNode node){
		String key = newNode.key;
		if(key.compareToIgnoreCase(node.key)==0){
			System.out.println("key already exists!!. No duplicates.");
			return;
		}
		if(key.compareToIgnoreCase(node.key)<0){
			if(node.leftChild==null){
				node.leftChild=newNode;
				newNode.parent=node;
			}else{
				insert(newNode,node.leftChild);
			}
		}else{
			if(node.rightChild==null){
				node.rightChild=newNode;
				newNode.parent=node;
			}else{
				insert(newNode,node.rightChild);
			}
		}
	}
	
	public User findUser(String key){
		BSTNode nodeIt=root;
		while(nodeIt!=null){
			String keyIt=nodeIt.key;
			if(key.compareToIgnoreCase(keyIt)==0){
				System.out.println(nodeIt.user.toString());
				return nodeIt.user;
			}else if(key.compareToIgnoreCase(keyIt)<0){
				nodeIt=nodeIt.leftChild;
			}else{
				nodeIt=nodeIt.rightChild;
			}
		}
		System.out.println("Key does not exist.");
		return null;
	}
	
	public void removeUser(String key){
		if(root==null){
			System.out.println("Tree is empty. No removes.");
			return;
		}
		
		if(key==root.key){
			removeRoot();
		}else{
			remove(key,root);
		}
	}
	
	private void removeRoot(){
		if(root.leftChild==null && root.rightChild==null){
			root=null;
		}
		if(root.leftChild==null || root.rightChild==null){
			if(root.leftChild==null){
				root=root.rightChild;
			}else{
				root=root.leftChild;
			}
		}else{
			remove(root.key,root);
		}
	}
	
	private boolean remove(String key, BSTNode node){
		if(node==null){
			System.out.println("Cannot remove: key does not exist");
			return false;
		}
		
		if(key.compareToIgnoreCase(node.key)<0){
			return remove(key,node.leftChild);
		}
		if(key.compareToIgnoreCase(node.key)>0){
			return remove(key,node.rightChild);
		}
		
		if(node.leftChild==null && node.rightChild==null){   //es un nodo hoja
			BSTNode parent = node.parent;
			if(parent.leftChild==node){
				parent.leftChild=null;
			}else{
				parent.rightChild=null;
			}
			return true;
		}
		
		if (node.leftChild==null || node.rightChild==null){  //tiene solo un hijo
			BSTNode grandchild = null;
			if(node.rightChild==null){
				grandchild=node.leftChild;
			}else{
				grandchild=node.rightChild;
			}
			
			BSTNode grandparent = node.parent;
			if(grandparent.leftChild==node){
				grandparent.leftChild=grandchild;
			}else{
				grandparent.rightChild=grandchild;
			}
			grandchild.parent=grandparent;
			return true;
		}
		
		BSTNode sucesorNode = findMin(node.rightChild);
		node.user=sucesorNode.user;
		node.key=sucesorNode.key;
		
		remove(sucesorNode.key,node.rightChild);
		return true;
		
	}
	
	private BSTNode findMin(BSTNode node){
		if(node==null){
			return null;
		}
		BSTNode minNode=node;
		while(minNode.leftChild!=null){
			minNode=minNode.leftChild;
		}
		return minNode;
	}
	
	
	
	

}
