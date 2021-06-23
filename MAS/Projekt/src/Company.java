import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity(name="Company")
public class Company{
	private static Set<String> names=new TreeSet<>();
	public static boolean checkName(String name){
		return names.contains(name);
	}
	public static void addName(String name){
		names.add(name);
	}

	@Id
	@Basic
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	private String name;

	@Basic
	public String getIndustry(){
		return industry;
	}
	public void setIndustry(String industry){
		this.industry=industry;
	}
	private String industry;

	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Local> getLocals(){
		return locals;
	}
	private void setLocals(List<Local> locals){
		this.locals=locals;
	}
	public void addLocal(Local local) throws Exception{
		if(local.getOwner().equals(this)){
			locals.add(local);
		}else{
			throw new Exception("Local is not owned by this company");
		}
	}
	private List<Local> locals=new ArrayList<>();

	protected Company(){}
	private Company(String name,String industry){
		this.name=name;
		this.industry=industry;
	}

	public static Company createCompany(String name,String industry){
		if(checkName(name)){
			throw new IllegalArgumentException("This name already exists");
		}
		addName(name);
		Company company=new Company(name,industry);
		return company;
	}

}
