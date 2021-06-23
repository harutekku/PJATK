package DAO;

import java.io.Serializable;
import java.util.List;

public interface IGeneric<E,Id extends Serializable>{
	void add(E entity);

	E update(E entity);

	void remove(E entity);

	E get(Id id);

	List<E> getAll();

	long getExtentSize();

}


