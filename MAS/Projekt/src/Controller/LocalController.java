package Controller;

import DAO.DAOGeneric;
import Models.Local;

import java.util.List;

/**
 * The type Local controller.
 */
public class LocalController{
	private DAOGeneric<Local,Long> LocalDAO=new DAOGeneric<>(Local.class);
	/**
	 * Get all list.
	 *
	 * @return the list
	 */
	public List<Local> getAll(){
		return LocalDAO.getAll();
	}
	/**
	 * Get local.
	 *
	 * @param id the id
	 * @return the local
	 */
	public Local get(long id){
		return LocalDAO.get(id);
	}
	/**
	 * Update local.
	 *
	 * @param local the local
	 * @return the local
	 */
	public Local update(Local local){
		return LocalDAO.update(local);
	}
	/**
	 * Add.
	 *
	 * @param local the local
	 */
	public void add(Local local){
		LocalDAO.add(local);
	}
	/**
	 * Remove.
	 *
	 * @param local the local
	 */
	public void remove(Local local){
		LocalDAO.remove(local);
	}
}
