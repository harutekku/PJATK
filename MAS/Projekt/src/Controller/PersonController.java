package Controller;

import DAO.DAOGeneric;
import Models.Person;

import java.util.List;

/**
 * The type Person controller.
 */
public class PersonController{
	private DAOGeneric<Person,String> personDAO=new DAOGeneric<>(Person.class);
	/**
	 * Get all list.
	 *
	 * @return the list
	 */
	public List<Person> getAll(){
		return personDAO.getAll();
	}
	/**
	 * Get person.
	 *
	 * @param id the id
	 * @return the person
	 */
	public Person get(String id){
		return personDAO.get(id);
	}
	/**
	 * Update person.
	 *
	 * @param person the person
	 * @return the person
	 */
	public Person update(Person person){
		return personDAO.update(person);
	}
	/**
	 * Add.
	 *
	 * @param person the person
	 */
	public void add(Person person){
		personDAO.add(person);
	}
	/**
	 * Remove.
	 *
	 * @param person the person
	 */
	public void remove(Person person){
		personDAO.remove(person);
	}

}
