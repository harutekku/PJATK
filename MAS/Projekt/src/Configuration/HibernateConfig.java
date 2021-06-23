package Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig{
	private static StandardServiceRegistry registry=null;
	private static SessionFactory sessionFactory=null;

	public static void StartHibernateConfiguration(){
		try{
			registry=new StandardServiceRegistryBuilder().configure().build();
			sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Exception e){
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	public static Session openSession(){
		return sessionFactory.openSession();
	}

}
