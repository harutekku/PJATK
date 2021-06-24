package Controller;

import DAO.DAOGeneric;
import Models.Person;

import java.util.List;

public class PersonController{
	private DAOGeneric<Person,String> personDAO=new DAOGeneric<>(Person.class);
	public List<Person> getAll(){
		return personDAO.getAll();
	}
	public Person get(String id){
		return personDAO.get(id);
	}
	public Person update(Person person){
		return personDAO.update(person);
	}
	public void add(Person person){
		personDAO.add(person);
	}
	public void remove(Person person){
		personDAO.remove(person);
	}

}
