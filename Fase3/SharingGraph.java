package Fase3;

import Fase1.LstRequest;
import Fase1.DNode;
import Fase1.Request;
import Fase2.DList;


public class SharingGraph implements IGraph {

	public int numVertices;
	public int maxVertices;

	public DListVertex[] vertices;
	public boolean directed;

	public String[] ciudades;


	public SharingGraph(String[] ciudades){
		maxVertices=10;
		numVertices=ciudades.length;
		this.ciudades=ciudades;
		vertices=new DListVertex[maxVertices];
		for (int i=0; i<numVertices;i++) {
			vertices[i]=new DListVertex(); 
		}
		directed=true;
	}

	public void setTravels(LstRequest offers){
		for(DNode nodeIt=offers.header.next;nodeIt!=offers.trailer;nodeIt=nodeIt.next){
			addEdge(getIndex(nodeIt.request.source),getIndex(nodeIt.request.target));
		}
	}

	private int getIndex(String elem){
		int z=-1;
		for(int i=0;i<ciudades.length;i++){
			if(elem.equals(ciudades[i])){
				return i;
			}
		}
		return z;
	}

	private String getCity(int z){
		return ciudades[z];
	}

	public String[] getListDestination(String city){
		int index=getIndex(city);
		String[] result= new String[getOutDegree(index)];
		if(result.length==0){
			return null;
		}
		int z=0;

		for(int i=0;i<numVertices;i++){
			if(isEdge(index, i)){
				result[z]=getCity(i);
				z++;
			}
		}
		return result;
	}

	public String[] getListOrigin(String city){
		int index=getIndex(city);
		String[] result= new String[getInDegree(index)];
		if(result.length==0){
			return null;
		}
		int z=0;

		for(int i=0;i<numVertices;i++){
			if(isEdge(i, index)){
				result[z]=getCity(i);
				z++;
			}
		}
		return result;
	}

	public void addVertex() {
		if (numVertices==maxVertices) {
			System.out.println("Cannot add new vertices!!!");
			return;
		}
		numVertices++;
		vertices[numVertices-1]=new DListVertex(); 
	}

	@Override
	public int sizeVertices() {
		return numVertices;
	}

	@Override
	public int sizeEdges() {
		int numEdges=0;
		for (int i=0;i<numVertices;i++) {
			if (vertices[i]!=null) numEdges=+vertices[i].getSize();
		}
		if (!directed) numEdges=numEdges/2;
		return numEdges;
	}


	public int getOutDegree(int i) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);

		int outdegree=0;
		outdegree=vertices[i].getSize();
		return outdegree;
	}

	public int getInDegree(int i) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		int indegree=0;
		for (int j=0; j<numVertices;j++) {
			if (vertices[j].contains(i)) indegree++;
		}
		return indegree;
	}

	@Override
	public int getDegree(int i) {
		int degree=0;
		if (directed) {
			degree=getOutDegree(i)+getInDegree(i);
		} else degree=vertices[i].getSize();
		return degree;
	}

	@Override
	public void addEdge(int i, int j) {
		//by default, we add an edge with value 1;
		addEdge(i,j,1);
	}
	//check if i is a right vertex
	private boolean checkVertex(int i) {
		if (i>=0 && i<numVertices) return true;
		else return false;
	}

	public void addEdge(int i, int j, float w) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + j);
		if (vertices[i].contains(j)) {
			int index=vertices[i].getIndexOf(j);
			DNodeVertex node=vertices[i].getAt(index);
			node.weight+=1;
		} else {
			vertices[i].addLast(j,w);

		}
	}
	public void removeEdge(int i, int j) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + j);

		int index=vertices[i].getIndexOf(j);
		vertices[i].removeAt(index);

		if (!directed) {
			index=vertices[j].getIndexOf(i);
			vertices[j].removeAt(index);
		}
	}

	@Override
	public boolean isEdge(int i, int j) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + j);

		boolean result=vertices[i].contains(j);
		return result;
	}

	@Override
	public Float getWeightEdge(int i, int j) {
		if (!checkVertex(i)) throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) throw new IllegalArgumentException("Nonexistent vertex  " + j);
		Float result=null;
		int index=vertices[i].getIndexOf(i);
		if (index!=-1) {
			result=vertices[i].getAt(index).weight;
		}
		return result;
	}

	@Override
	public void show() {

		for (int i=0; i<numVertices; i++) {
			if (vertices[i]!=null) {
				System.out.println("adjacentes vertices for vertex  " + i + ": " + vertices[i].toString() );
			}
		}

	}

	public int[] getAdjacents(int i) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		//gets the number of adjacent vertices
		int numAdj=vertices[i].getSize();
		//creates the array
		int[] adjVertices=new int[numAdj];
		//saves the adjacent vertices into the array
		for (int j=0; j<numAdj; j++) {
			adjVertices[j]=vertices[i].getAt(j).vertex;
		}
		//return the array with the adjacent vertices of i
		return adjVertices;
	}

	public void breadth() {
		System.out.println("breadth traverse of the graph:");

		//to mark when a vertex has already been shown
		boolean visited[]=new boolean[numVertices];

		//we have to traverse all vertices
		for (int i=0;i<numVertices;i++) {
			if (!visited[i]) { //we only process the non-visited vertex
				breadth(i,visited);
			}
		}
		System.out.println();

	}

	//breadth order for the vertex i 
	protected void breadth(int i, boolean[] visited) {
		//this array helps to mark what vertices have been stored into the queue
		boolean stored[]=new boolean[numVertices];
		//System.out.println("breadth traverse for " + i);
		//we use a queue to save the adjacent vertices that we visit
		SQueue q=new SQueue();
		//enqueue the first
		q.enqueue(i);
		//while the queue is not empty
		while (!q.isEmpty()) {
			//gets the first
			int vertex=q.dequeueInt();
			//shows the vertex and marks it as visited
			System.out.print(vertex+"\t");
			visited[vertex]=true;
			//gets its adjacent vertices
			int[] adjacents=getAdjacents(vertex);
			for(int adjVertex:adjacents) {
				//enqueue only those that have not been visited or stored yet 
				if (!visited[adjVertex] && !stored[adjVertex]) {
					q.enqueue(adjVertex);
					stored[adjVertex]=true;
				}
			}
		}
	}

	public void depth() {
		System.out.println("depth traverse of the graph:");
		//to mark when a vertex has already been shown
		boolean visited[]=new boolean[numVertices];
		//we have to traverse all vertices
		for (int i=0;i<numVertices;i++) {
			if (!visited[i]) {
				depth(i,visited);
			}
		}
		System.out.println();

	}

	protected void depth(int i,boolean[] visited) {
		//prints the vertex and marks as visited
		System.out.print(i+"\t");
		visited[i]=true;
		//gets its adjacent vertices
		int[] adjacents=getAdjacents(i);
		for (int adjV:adjacents) {
			if (!visited[adjV]) {
				//only depth traverses those adjacent vertices 
				//that have not been visited yet
				depth(adjV,visited);

			}
		}
	}

	public String[] getAllCities(){
		DList lista = new DList();
		String[] result=new String[numVertices];
		//to mark when a vertex has already been shown
		boolean visited[]=new boolean[numVertices];
		//we have to traverse all vertices
		for (int i=0;i<numVertices;i++) {
			if (!visited[i]) {
				getAllCities(i,visited,lista);
			}
		}
		for(int i=0;i<lista.getSize();i++){
			result[i]=lista.getAt(i);
		}
		System.out.println();
		return result;
	}

	private void getAllCities(int i, boolean[] visited,DList lista){
		SStack s = new SStack();
		s.push(i);
		while(!s.isEmpty()){
			int vertex=s.pop();
			lista.addLast(getCity(vertex));
			visited[vertex]=true;
			int[] adjacents=getAdjacents(vertex);
			for(int adjVertex:adjacents) {
				if(!visited[adjVertex]){
					s.push(adjVertex);
					getAllCities(s.pop(), visited, lista);
				}
			}
		}
	}

	public Pair[] nonConnectedCities() {
		SQueue q=new SQueue();
		boolean viewed[]=new boolean[numVertices];
		for (int i=0;i<numVertices;i++) {
			if (!viewed[i]) {
				nonConnectedCities(i,viewed);
			}
			for (int x=0; x<viewed.length; x++) {
				if(viewed[x]==false) {
					Pair pair = new Pair(ciudades[i], ciudades[x]);
					q.enqueue(pair);
				}
				viewed[x]=false;
			}
		}
		Pair[] parejas=new Pair[q.size];
		for (int i = 0; i < parejas.length; i++) {
			parejas[i]=q.dequeueP();
		}
		return parejas;
	}
	private void nonConnectedCities(int i, boolean[] viewed) {
		boolean stored[]=new boolean[numVertices];
		SQueue q=new SQueue();
		q.enqueue(ciudades[i]);
		while (!q.isEmpty()) {
			String vertex=q.dequeue();
			viewed[getIndex(vertex)]=true;
			int[] adjacents=getAdjacents(getIndex(vertex));
			for(int adjVertex:adjacents) {
				if (!viewed[adjVertex] && !stored[adjVertex]) {
					q.enqueue(ciudades[adjVertex]);
					stored[adjVertex]=true;
				}
			}
		}
	}


	public static void main(String args[]) {
		/*0 Madrid
		 *1 Barcelona
		 *2 Valencia
		 *3 Sevilla
		 *4 Bilbao
		 *5 Granada
		 *6 Toledo
		 *7 Salamanca
		 *8 Alicante
		 *9 Caceres
		 */
		final String ciudades[]={"Madrid",
				"Barcelona", "Valencia","Sevilla","Bilbao",
				"Granada","Toledo","Salamanca","Alicante","Caceres"};
		
		SharingGraph graph=new SharingGraph(ciudades);	
		System.out.println();
		
		LstRequest lst =new LstRequest();
		lst.addLast(new Request(null,"Madrid","Sevilla"));
		lst.addLast(new Request(null,"Madrid","Barcelona"));
		lst.addLast(new Request(null,"Bilbao","Madrid"));
		lst.addLast(new Request(null,"Valencia","Madrid"));
		lst.addLast(new Request(null,"Valencia","Barcelona"));
		lst.addLast(new Request(null,"Madrid","Barcelona"));
		lst.addLast(new Request(null,"Sevilla","Madrid"));
		lst.addLast(new Request(null,"Madrid","Sevilla"));
		lst.addLast(new Request(null,"Alicante","Barcelona"));
		lst.addLast(new Request(null,"Valencia","Madrid"));
		lst.addLast(new Request(null,"Valencia","Barcelona"));
		lst.addLast(new Request(null,"Madrid","Sevilla"));
		lst.addLast(new Request(null,"Madrid","Toledo"));
		lst.addLast(new Request(null,"Toledo","Salamanca"));
		lst.addLast(new Request(null,"Toledo","Salamanca"));
		lst.addLast(new Request(null,"Salamanca","Barcelona"));
		lst.addLast(new Request(null,"Toledo","Caceres"));
		lst.addLast(new Request(null,"Barcelona","Toledo"));
		
		graph.setTravels(lst);
		graph.show();
		graph.depth();
		System.out.println();
		String[] result = graph.getAllCities();
		for(int i=0;i<result.length;i++){
			System.out.println(result[i]);
		}
		Pair[] noo = graph.nonConnectedCities();
		for(int i=0;i<noo.length;i++){
			System.out.println(noo[i]);
		}
	}


}
