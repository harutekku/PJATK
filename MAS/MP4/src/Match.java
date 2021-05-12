import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum Result{Host,Guest,Dead_heat};

public class Match{
	private Team host;
	private Team guest;

	private List<Participation> participations; // nie wiem czy potrzebne
	private LocalDateTime date;
	private Result winner;

	public Match(Team hostTeam,Team guestTeam,LocalDateTime date){
		this.host=hostTeam;
		host.addHostMatch(this);
		this.guest=guestTeam;
		guest.addGuestMatch(this);
		this.participations=new ArrayList<>();
		this.date=date;
		winner=Result.Dead_heat;
	}

	public void setWinner(Result result){
		winner=result;
	}

	public Team getWinner(){
		if(winner==Result.Host){
			return host;
		}else if(winner==Result.Guest){
			return guest;
		}else{
			return null;
		}
	}

	public void addParticipation(Participation participation){
		participations.add(participation);
	}

	public List<Participation> getParticipations(){
		return participations;
	}

	public List<Player> getPlayers(){
		List<Player> players=new ArrayList<>();
		for(Participation participation: getParticipations()){
			if(!players.contains(participation.getPlayer())){
				players.add(participation.getPlayer());
			}
		}
		return players;
	}

	public String showPlayers(){
		String result="List of players";
		for(Player player: getPlayers()){
			result+=player.toString()+'\n';
		}
		return result;
	}

	@Override
	public String toString(){
		return "Match{"+
				"host="+host+
				", guest="+guest+
				", date="+date+
				", winner="+winner+
				'}';
	}
}
