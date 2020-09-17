package Fase1;

public class SharingCar {

	public static LstRequest mergeAlternateRequests(LstRequest p1, LstRequest p2){
		LstRequest lista = new LstRequest();
		LstRequest original_1 = new LstRequest();
		LstRequest original_2 = new LstRequest();
		for(DNode nodeIt = p1.header.next;nodeIt!=p1.trailer;nodeIt=nodeIt.next){
			original_1.addLast(nodeIt.request);
		}
		for(DNode nodeIt = p2.header.next;nodeIt!=p2.trailer;nodeIt=nodeIt.next){
			original_2.addLast(nodeIt.request);
		}
		if(original_1.getSize()>original_2.getSize()){
			while(original_1.isEmpty()==false){
				if (original_1.isEmpty()==false){
					lista.addLast(original_1.getFirst());
					original_1.removeFirst();
				}
				if(original_2.isEmpty()==false){
					lista.addLast(original_2.getFirst());
					original_2.removeFirst();
				}
			}	
		}else{
			while(original_2.isEmpty()==false){
				if (original_1.isEmpty()==false){
					lista.addLast(original_1.getFirst());
					original_1.removeFirst();
				}
				if(original_2.isEmpty()==false){
					lista.addLast(original_2.getFirst());
					original_2.removeFirst();
				}
			}	
		}
		return lista;

	}

	public static LstRequest sharing (LstRequest p1, LstRequest p2){
		LstRequest lista = new LstRequest();
		for(DNode nodeIt=p1.header.next;nodeIt!=p1.trailer;nodeIt=nodeIt.next){
			for(DNode nodeIt2=p2.header.next;nodeIt2!=p2.trailer;nodeIt2=nodeIt2.next){
				if(nodeIt.request.source.equals(nodeIt2.request.source)&& nodeIt.request.target.equals(nodeIt2.request.target)){
					lista.addLast(nodeIt.request);
					lista.addLast(nodeIt2.request);
				}
			}
		}
		return lista;
	}

	public static LstRequest sort (LstRequest p1,Integer num){
		LstRequest original = new LstRequest();
		LstRequest auxiliar = new LstRequest();
		int z=0;
		int k=p1.getSize();
		for(int i=0;i<p1.getSize();i++){
			original.addLast(p1.getAt(i));
		}
		int i=0;
		int l=0;
		if(num.equals(1)){
			while(z<k){
				i=0;
				while(i<original.getSize()-2){   //comprueba hasta el penultimo
					if(original.getAt(i).source.equals(original.getAt(i+1).source)){

					}else{
						if(original.getAt(i).source.compareToIgnoreCase(original.getAt(i+1).source)<0){  //Z es el mayor
							auxiliar.addLast(original.getAt(i));
							original.removeAt(i);
							original.insertAt(i+1, auxiliar.getLast());
							auxiliar.removeLast();
						}
					}
					i++;
				}

				l=original.getSize()-1;
				while(l>0){
					if(original.getAt(l).source.equals(original.getAt(l-1).source)){   //ya esta ordenado
						
					}else{
						if(original.getAt(l).source.compareToIgnoreCase(original.getAt(l-1).source)>0){
							auxiliar.addLast(original.getAt(l));
							original.removeAt(l);
							original.insertAt(l-1, auxiliar.getLast());
							auxiliar.removeLast();
						}
					}
					l--;
				}
				z++;
			}
		
		}else if (num.equals(2)){
			while(z<k){
				i=0;
				while(i<original.getSize()-2){   //comprueba hasta el penultimo
					if(original.getAt(i).target.equals(original.getAt(i+1).target)){

					}else{
						if(original.getAt(i).target.compareToIgnoreCase(original.getAt(i+1).target)<0){ 
							auxiliar.addLast(original.getAt(i));
							original.removeAt(i);
							original.insertAt(i+1, auxiliar.getLast());
							auxiliar.removeLast();
						}
					}
					i++;
				}
					l=original.getSize()-1;
					while(l>0){
						if(original.getAt(l).target.equals(original.getAt(l-1).target)){   //ya esta ordenado
							
						}else{
							if(original.getAt(l).target.compareToIgnoreCase(original.getAt(l-1).target)>0){
								auxiliar.addLast(original.getAt(l));
								original.removeAt(l);
								original.insertAt(l-1, auxiliar.getLast());
								auxiliar.removeLast();
							}
						}
						l--;
					}
				z++;
			}
		}else{
			while(z<k){
				i=0;
				while(i<original.getSize()-2){   //comprueba hasta el penultimo
					if(original.getAt(i).login.equals(original.getAt(i+1).login)){
						
					}else{
						if(original.getAt(i).login.compareToIgnoreCase(original.getAt(i+1).login)<0){  
							auxiliar.addLast(original.getAt(i));
							original.removeAt(i);
							original.insertAt(i+1, auxiliar.getLast());
							auxiliar.removeLast();
						}
					}
					i++;
				}
					l=original.getSize()-1;
					while(l>0){
						if(original.getAt(l).login.equals(original.getAt(l-1).login)){   //ya esta ordenado
							
						}else{
							if(original.getAt(l).login.compareToIgnoreCase(original.getAt(l-1).login)>0){
								auxiliar.addLast(original.getAt(l));
								original.removeAt(l);
								original.insertAt(l-1, auxiliar.getLast());
								auxiliar.removeLast();
							}
						}
						l--;
					}
				z++;
			}
		}
		LstRequest def = new LstRequest();
		for(int m=original.getSize()-1;m>=0;m--){
			def.addLast(original.getAt(m));
		}
		return def;
	}

	public static LstRequest removeDuplicates(LstRequest p1){
		LstRequest original = new LstRequest();
		for(DNode nodeIt = p1.header.next;nodeIt!=p1.trailer;nodeIt=nodeIt.next){
			original.addLast(nodeIt.request);
		}
		for(int i=0;i<p1.getSize()-1;i++){
			for(int z=i+1;z<p1.getSize();z++){
				if(i!=z){
					if(p1.getAt(i).login.equals(p1.getAt(z).login) && p1.getAt(i).source.equals(p1.getAt(z).source)&&
							p1.getAt(i).target.equals(p1.getAt(z).target)){
						original.removeAt(z);
					}
				}
				
			}
		}
		return original;
	}

	public static LstRequest searchOrigin (String elem, LstRequest p1){   //searchSource
		LstRequest lista = new LstRequest();
		for(DNode nodeIt=p1.header.next;nodeIt!=p1.trailer;nodeIt=nodeIt.next){
			if(nodeIt.request.source.equals(elem)){
				lista.addLast(nodeIt.request);
			}
		}
		return lista;	
	}

	public static LstRequest searchDestination (String elem, LstRequest p1){   //searchTarget
		LstRequest lista = new LstRequest();
		for(DNode nodeIt=p1.header.next;nodeIt!=p1.trailer;nodeIt=nodeIt.next){
			if(nodeIt.request.target.equals(elem)){
				lista.addLast(nodeIt.request);
			}
		}
		return lista;	
	}

	public static void main (String[] args){
		
		LstRequest lst1 = new LstRequest();
		lst1.addLast(new Request("user1","Madrid","Barcelona"));
		lst1.addLast(new Request("user2","Alicante","Barcelona"));
		lst1.addLast(new Request("user3","Sevilla","Alicante"));
		lst1.addLast(new Request("user4","Valencia","Sevilla"));

		LstRequest lst2=new LstRequest();
		lst2.addLast(new Request("user5","Madrid","Sevilla"));
		lst2.addLast(new Request("user6","Madrid","Barcelona"));
		lst2.addLast(new Request("user7","Bilbao","Madrid"));
		lst2.addLast(new Request("user8","Valencia","Madrid"));
		lst2.addLast(new Request("user9","Valencia","Barcelona"));
		lst2.addLast(new Request("user6","Madrid","Barcelona"));
		lst2.addLast(new Request("user11","Sevilla","Madrid"));
		lst2.addLast(new Request("user12","Alicante","Barcelona"));
		
		System.out.println(lst1.toString());
		
		System.out.println();
		
		System.out.println(lst2.toString());

		System.out.println(sharing(lst1, lst2).toString());
		
		System.out.println(mergeAlternateRequests(lst1, lst2));
		
		System.out.println(sort(lst2, 1));
		
		System.out.println();
		
		System.out.println(sort(lst1, 2));
		
		System.out.println(removeDuplicates(lst2).toString());
		
		System.out.println(searchDestination("Caceres",lst1));
		
		System.out.println(searchOrigin("Madrid",lst2).toString());
		
	}

}
