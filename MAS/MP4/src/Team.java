import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Team{
	private final static int maxPlayers=30;
	private static Map<String,Team> names=new TreeMap<>();

	public static Team getTeam(String name){
		return names.get(name);
	}

	public static boolean checkName(String name){
		return names.containsKey(name);
	}

	public static void addTeam(Team team){
		names.put(team.getName(),team);
	}

	private String name;
	private List<Player> members;
	private Player captain;
	private List<Match> hostMatches;
	private List<Match> guestMatches;
	private Set<Sponsor> sponsorSet;

	public Team(String name){
		this.name=name;
		if(checkName(name)){
			throw new IllegalArgumentException("This name already exist");
		}
		addTeam(this);
		this.members=new ArrayList<>();
		this.hostMatches=new ArrayList<>();
		this.guestMatches=new ArrayList<>();
		sponsorSet=new TreeSet<>((o1,o2)->(int)(o1.getDate().until(o2.getDate(),ChronoUnit.MILLIS)));
	}

	public void addMember(Player player){
		if(getMembers().size() >= maxPlayers){
			System.out.println("Max players of "+maxPlayers+" reached");
			return;
		}
		if(getMembers().contains(player)){
			System.out.println("Player already exist in this team");
			return;
		}
		if(getCaptain()==null){//przypisuje kapitana na pierwszego dodanego gracza, w celu wyeliminowania możliwości że w grupie nie ma kapitana
			members.add(player);
			setCaptain(player);
			player.setTeam(this);
			player.setTeamCaptain(this);
		}else{
			members.add(player);
			player.setTeam(this);
		}
	}

	public void setCaptain(Player player){
		if(members.contains(player)){
			if(captain!=null){
				captain.setTeamCaptain(null);
			}
			captain=player;
			captain.setTeamCaptain(this);
		}else{
			System.out.println("Cant set captain who is not in team");
		}
	}

	public void addHostMatch(Match match){
		hostMatches.add(match);
	}

	public void addGuestMatch(Match match){
		guestMatches.add(match);
	}

	public List<Player> getMembers(){
		return members;
	}

	public Player getCaptain(){
		return captain;
	}

	public String getName(){
		return name;
	}

	public void addSponsor(String name,LocalDateTime date){
		sponsorSet.add(new Sponsor(name,date,this));
	}

	public String getSponsors(){
		String result="";
		for(Sponsor sponsor:sponsorSet){
			result+=sponsor.toString()+'\n';
		}
		return result;
	}

	@Override
	public String toString(){
		return "Team{"+
				"name='"+name+'\''+
				", captain="+captain+
				'}';
	}
}
