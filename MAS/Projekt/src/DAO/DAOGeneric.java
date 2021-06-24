package DAO;

import Configuration.HibernateConfig;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * The type Dao generic.
 *
 * @param <E>  the type parameter
 * @param <Id> the type parameter
 */
public class DAOGeneric<E,Id extends Serializable> implements IGeneric<E,Id>{
	private final Class classType;
	private final String type;

	/**
	 * Instantiates a new Dao generic.
	 *
	 * @param classType the class type
	 */
	public DAOGeneric(Class classType){
		this.classType=classType;
		this.type=classType.getName().substring(classType.getName().lastIndexOf(".") + 1);
	}

	@Override
	public void add(E entity){
		Session session=HibernateConfig.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}
	@Override
	public E update(E entity){
		Session session=HibernateConfig.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
		return entity;
	}
	@Override
	public void remove(E entity){
		Session session=HibernateConfig.openSession();
		session.beginTransaction();
		session.remove(entity);
		session.getTransaction().commit();
		session.close();
	}
	@Override
	public E get(Serializable id){
		Session session=HibernateConfig.openSession();
		session.beginTransaction();
		//session.get();
		E e=(E)session.get(type,id);
		session.getTransaction().commit();
		session.close();
		return e;
	}
	@Override
	public List<E> getAll(){
		Session session=HibernateConfig.openSession();
		session.beginTransaction();
		List<E> e=(List<E>)session.createQuery("from "+type).list();
		session.getTransaction().commit();
		session.close();
		return e;
	}
	@Override
	public long getExtentSize(){
		return 0;
	}

}
