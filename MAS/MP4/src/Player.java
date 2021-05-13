import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Player{
	private final static int maxParticipations=3;

	public static int getMaxParticipations(){
		return maxParticipations;
	}

	private String name, surname;
	private LocalDate dateOfBirth;
	private Team teamMember;
	private Team teamCaptain;

	private List<Participation> participations;

	public Player(String name,String surname,LocalDate dateOfBirth){
		this.name=name;
		this.surname=surname;
		this.dateOfBirth=dateOfBirth;
		this.participations=new ArrayList<>();
	}

	public void setTeam(Team team){
		teamMember=team;
	}

	public void setTeamCaptain(Team team){
		teamCaptain=team;
	}

	public boolean checkParticipation(Match match){
		int counter=0;
		for(Participation participation: participations){
			if(participation.getMatch().equals(match)){
				counter++;
			}
		}
		if(counter<maxParticipations) return false;
		return true;
	}

	public List<Participation> getParticipations(){
		return participations;
	}

	public void addParticipation(Participation participation){
		getParticipations().add(participation);
	}

	@Override
	public String toString(){
		return name+" "+surname+" from team "+teamMember.getName();
	}
}
