package Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Level;

/**
 * The type Hibernate config.
 */
public class HibernateConfig{
	private static StandardServiceRegistry registry=null;
	private static SessionFactory sessionFactory=null;

	/**
	 * Start hibernate configuration.
	 */
	public static void StartHibernateConfiguration(){
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		try{
			registry=new StandardServiceRegistryBuilder().configure().build();
			sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	/**
	 * Open session session.
	 *
	 * @return the session
	 */
	public static Session openSession(){
		return sessionFactory.openSession();
	}

}
