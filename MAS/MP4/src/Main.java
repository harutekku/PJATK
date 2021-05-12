import XOR.Account;
import XOR.Corporation;
import XOR.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{
	public static void main(String[] args){

		Team team1=new Team("Mydelniczki");
		Team team2=new Team("Goferki");
		Team team3=new Team("Kapustki");
		try{
			Team team4=new Team("Kapustki");
		}catch(Exception e){
			e.printStackTrace();
		}

		List<Player> playerList=new ArrayList<>();
		for(int i=0;i<80;i++){
			playerList.add(new Player(get(),get(),LocalDate.of(random.nextInt(40)+1950,random.nextInt(11)+1,random.nextInt(27)+1)));
		}

		for(int i=0;i<31;i++){
			team1.addMember(playerList.get(i));
		}
		for(int i=31;i<45;i++){
			team2.addMember(playerList.get(i));
		}
		for(int i=46;i<70;i++){
			team3.addMember(playerList.get(i));
		}

		Match match=new Match(team1,team2,LocalDateTime.of(2020,05,21,14,00));
		for(int i=0;i<10;i++){
			try{
				new Participation(team1.getMembers().get(i),match,LocalTime.of(14,00));
				new Participation(team2.getMembers().get(i),match,LocalTime.of(14,00));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			new Participation(team1.getMembers().get(11),match,LocalTime.of(14,00),LocalTime.of(15,00));
			new Participation(team2.getMembers().get(11),match,LocalTime.of(14,00),LocalTime.of(15,00));
			new Participation(team1.getMembers().get(12),match,LocalTime.of(15,00),LocalTime.of(15,45));
			new Participation(team2.getMembers().get(12),match,LocalTime.of(15,00),LocalTime.of(15,45));
			new Participation(team1.getMembers().get(11),match,LocalTime.of(15,45));
			new Participation(team2.getMembers().get(11),match,LocalTime.of(15,45));
			new Participation(team2.getMembers().get(11),match,LocalTime.of(15,45));
			new Participation(team2.getMembers().get(11),match,LocalTime.of(15,45));
		}catch(Exception e){
			e.printStackTrace();
		}
		match.setWinner(Result.Guest);

		System.out.println(team1.getCaptain());
		System.out.println(team1.getMembers().get(0));

		team1.addSponsor("XTB",LocalDateTime.now());
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		team1.addSponsor("MBANK",LocalDateTime.now());
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		team1.addSponsor("PKO",LocalDateTime.now());

		System.out.println(team1.getSponsors());

		for(Participation participation:team1.getCaptain().getParticipations()){
			System.out.println(participation.getMatch());
		}


		Person person=new Person(get(),get());
		Person person2=new Person(get(),get());
		Corporation corporation=new Corporation(get());
		Account account=new Account(get(),3000,get(),person);
		try{
			account.setOwner(person2);
			account.setOwner(corporation);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static Random random=new Random();

	public static String get(){
		String result="";
		for(int i=0;i<10;i++){
			result+=(char)(random.nextInt(26)+97);
		}
		return result;
	}
}
