import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Participation{
	private Player player;
	private Match match;
	private LocalTime timeOfEntry;
	private LocalTime timeOfDescent;


	public Participation(Player player,Match match,LocalTime timeOfEntry) throws Exception{
		if(player.checkParticipation(match))
			throw new Exception("More than "+Player.getMaxParticipations()+" participations");
		this.player=player;
		player.addParticipation(this);
		this.match=match;
		match.addParticipation(this);
		this.timeOfEntry=timeOfEntry;
	}

	public Participation(Player player,Match match,LocalTime timeOfEntry,LocalTime timeOfDescent) throws Exception{
		if(player.checkParticipation(match))
			throw new Exception("More than "+Player.getMaxParticipations()+" participations");
		this.player=player;
		player.addParticipation(this);
		this.match=match;
		match.addParticipation(this);
		this.timeOfEntry=timeOfEntry;
		this.timeOfDescent=timeOfDescent;
	}

	public Player getPlayer(){
		return player;
	}

	public Match getMatch(){
		return match;
	}

	public long lengthOfContest(){
		return timeOfEntry.until(timeOfDescent,ChronoUnit.MINUTES);
	}
}
