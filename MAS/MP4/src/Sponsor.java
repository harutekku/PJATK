import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sponsor{
	private String name;
	private LocalDateTime date;
	private Team team;

	public Sponsor(String name,LocalDateTime date,Team team){
		this.name=name;
		this.date=date;
		this.team=team;
	}

	public LocalDateTime getDate(){
		return date;
	}

	@Override
	public String toString(){
		return "Sponsor{"+
				"name='"+name+'\''+
				", date="+date+
				'}';
	}
}
