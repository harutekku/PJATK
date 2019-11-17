/**
 *
 *  @author Pawłowicz Jakub S18688
 *
 */

package zad2;


import java.util.Scanner;

public class Purchase {
	String idClient, name, article;
	Double price, quantity, sum;

	public Purchase(String data){
		Scanner scan=new Scanner(data).useDelimiter(";");
		idClient=scan.next();
		name=scan.next();
		article=scan.next();
		price=Double.valueOf(scan.next());
		quantity=Double.valueOf(scan.next());
		scan.close();
		sum=price*quantity;
	}

	public String getIdClient(){
		return idClient;
	}

	public String getName(){
		return name;
	}

	public Double getSum(){
		return sum;
	}

	@Override
	public String toString(){
		return idClient+";"+name+";"+article+";"+price+";"+quantity;
	}
	String toStringWithKoszty(){
		return this+" (koszt: "+getSum()+")";
	}
}
