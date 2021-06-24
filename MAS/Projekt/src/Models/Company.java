package Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type Company.
 */
@Entity(name="Company")
public class Company{
	private static Set<String> names=new TreeSet<>();
	/**
	 * Check name boolean.
	 *
	 * @param name the name
	 * @return the boolean
	 */
	public static boolean checkName(String name){
		return names.contains(name);
	}
	/**
	 * Add name.
	 *
	 * @param name the name
	 */
	public static void addName(String name){
		names.add(name);
	}

	/**
	 * Get name string.
	 *
	 * @return the string
	 */
	@Id
	@Basic
	public String getName(){
		return name;
	}
	/**
	 * Set name.
	 *
	 * @param name the name
	 */
	public void setName(String name){
		this.name=name;
	}
	private String name;

	/**
	 * Get industry string.
	 *
	 * @return the string
	 */
	@Basic
	public String getIndustry(){
		return industry;
	}
	/**
	 * Set industry.
	 *
	 * @param industry the industry
	 */
	public void setIndustry(String industry){
		this.industry=industry;
	}
	private String industry;

	/**
	 * Get locals list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Local> getLocals(){
		return locals;
	}
	private void setLocals(List<Local> locals){
		this.locals=locals;
	}
	/**
	 * Add local.
	 *
	 * @param local the local
	 * @throws Exception the exception
	 */
	public void addLocal(Local local) throws Exception{
		if(local.getOwner().equals(this)){
			locals.add(local);
		}else{
			throw new Exception("Models.Local is not owned by this company");
		}
	}
	private List<Local> locals=new ArrayList<>();

	/**
	 * Instantiates a new Company.
	 */
	protected Company(){}
	private Company(String name,String industry){
		this.name=name;
		this.industry=industry;
	}

	/**
	 * Create company company.
	 *
	 * @param name     the name
	 * @param industry the industry
	 * @return the company
	 */
	public static Company createCompany(String name,String industry){
		if(checkName(name)){
			throw new IllegalArgumentException("This name already exists");
		}
		addName(name);
		Company company=new Company(name,industry);
		return company;
	}

}
