import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;

public class Main{
	public static void main(String[] args){
		//System.out.println("maslo");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		StandardServiceRegistry registry=null;
		SessionFactory sessionFactory=null;
		try{
			registry=new StandardServiceRegistryBuilder().configure().build();
			sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();

			/*Person user=Person.createUser("Andrzej","Jodłowski","727272727","bao@bao.pl","andrzejj","passXD");
			Person user2=Person.createUser("Marysia","Jodłowska","727272727","bao@bao.pl","marysia","passXD");
			session.save(user);
			session.save(user2);
			try{
				Person user3=Person.createUser("Marysia","Mirowska","727272727","bao@bao.pl","marysia","passXD");
				session.save(user3);
			}catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			CreditCard card=CreditCard.addCardToPerson(user,"12345",LocalDate.of(2023,12,22),"123");
			session.save(card);*/


			//session.remove(card);
			//session.remove(user);


			List<Person> users=session.createQuery("from Person",Person.class).getResultList();
			for(Person person:users){
				System.out.println(person);
				for(CreditCard card:person.getCreditCards()){
					System.out.println(card);
				}
			}

			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}finally{
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
	}
}
