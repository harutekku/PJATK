package Controller;

import DAO.DAOGeneric;
import Models.Local;

import java.util.List;

public class LocalController{
	private DAOGeneric<Local,Long> LocalDAO=new DAOGeneric<>(Local.class);
	public List<Local> getAll(){
		return LocalDAO.getAll();
	}
	public Local get(long id){
		return LocalDAO.get(id);
	}
	public Local update(Local local){
		return LocalDAO.update(local);
	}
	public void add(Local local){
		LocalDAO.add(local);
	}
	public void remove(Local local){
		LocalDAO.remove(local);
	}
}
