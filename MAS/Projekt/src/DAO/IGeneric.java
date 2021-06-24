package DAO;

import java.io.Serializable;
import java.util.List;

/**
 * The interface Generic.
 *
 * @param <E>  the type parameter
 * @param <Id> the type parameter
 */
public interface IGeneric<E,Id extends Serializable>{
	/**
	 * Add.
	 *
	 * @param entity the entity
	 */
	void add(E entity);

	/**
	 * Update e.
	 *
	 * @param entity the entity
	 * @return the e
	 */
	E update(E entity);

	/**
	 * Remove.
	 *
	 * @param entity the entity
	 */
	void remove(E entity);

	/**
	 * Get e.
	 *
	 * @param id the id
	 * @return the e
	 */
	E get(Id id);

	/**
	 * Gets all.
	 *
	 * @return the all
	 */
	List<E> getAll();

	/**
	 * Gets extent size.
	 *
	 * @return the extent size
	 */
	long getExtentSize();

}


