import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s18688{
	public static void main(String[] args){
		try{
			oraj(args[0]);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Zle argumenty");
		}
	}

	public static void oraj(String filename){
		try{
			Scanner scan=new Scanner(new File(filename));
			Tree root=new Tree();
			List list=new List();
			while(scan.hasNext()){
				String line=scan.nextLine();
				if(line!=""){
					Tree node=root.addKey(line.substring(0,1),line.length()>1?line.substring(2):"");
					list=list.add(node);
				}
			}
			scan.close();
			//list.show();
			List leaves=list.cutListToLeaves();
			//leaves.show();
			Tree last=leaves.searchLastWord();
			System.out.println(last.getWord());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}

class Tree{
	String key;
	Tree left_child, right_child, father;


	boolean ifLeaves(){
		return left_child==null&&right_child==null;
	}

	String getKey(){
		return key;
	}

	String getWord(){
		if(father==null) return getKey();
		return getKey()+father.getWord();
	}

	static Tree compareWords(Tree one, Tree two){
		String w1=one.getWord(), w2=two.getWord();
		if(w1.compareTo(w2)>0) return one;
		return two;
	}

	Tree addKey(String value,String road){
		Tree actual=this;
		for(int i=0;i<road.length();i++){
			char direction=road.charAt(i);
			if(direction=='R'){
				if(actual.right_child==null){
					actual.right_child=new Tree();
					actual.right_child.father=actual;
				}
				actual=actual.right_child;
			}else if(direction=='L'){
				if(actual.left_child==null){
					actual.left_child=new Tree();
					actual.left_child.father=actual;
				}
				actual=actual.left_child;
			}
		}
		actual.key=value;
		return actual;
	}
}

class List{
	Tree value;
	List next;

	public List(){
		value=null;
		next=null;
	}

	public List(Tree value){
		this.value=value;
		next=null;
	}

	public List add(Tree node){
		if(value==null){
			value=node;
			return this;
		}else{
			List actual=new List(node);
			actual.next=this;
			return actual;
		}
	}

	public void show(){
		List actual=this;
		while(actual!=null){
			System.out.print(actual.value.key+" ");
			actual=actual.next;
		}
		System.out.println();
	}

	public List cutListToLeaves(){
		List leaves=new List();
		List actual=this;
		while(actual!=null){
			if(actual.value.ifLeaves()){
				leaves=leaves.add(actual.value);
			}
			actual=actual.next;
		}
		return leaves;
	}

	public Tree searchLastWord(){
		List actual=this;
		Tree lastWord=actual.value;
		while(actual!=null){
			lastWord=Tree.compareWords(lastWord,actual.value);
			actual=actual.next;
		}
		return lastWord;
	}

}
