/**
 * @author Pawłowicz Jakub S18688
 */

package zad2;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersPurchaseSortFind{
	List<Purchase> list;

	public void readFile(String fname){
		try{
			BufferedReader br=new BufferedReader(new FileReader(new File(fname)));
			list=new ArrayList<>();
			String line;
			while((line=br.readLine())!=null){
				list.add(new Purchase(line));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void showSortedBy(String column){
		if(column.equals("Nazwiska")){
			this.list.sort((Purchase p1,Purchase p2)->{
				if(p1.getName().equals(p2.getName())) return p1.getIdClient().compareTo(p2.getIdClient());
				else return p1.getName().compareTo(p2.getName());
			});
			System.out.println("Nazwiska");
			for(Purchase p: list){
				System.out.println(p);
			}
		}else if(column.equals("Koszty")){
			this.list.sort((Purchase p1,Purchase p2)->{
				if(p1.getSum().equals(p2.getSum())) return p1.getIdClient().compareTo(p2.getIdClient());
				else return p2.getSum().compareTo(p1.getSum());
			});
			System.out.println("Koszty");
			for(Purchase p: list){
				System.out.println(p.toStringWithKoszty());
			}
		}
		System.out.println();


	}

	public void showPurchaseFor(String id){
		System.out.println("Klient "+id);
		for(Purchase p:list){
			if(p.getIdClient().equals(id)) System.out.println(p);
		}
		System.out.println();
	}
}