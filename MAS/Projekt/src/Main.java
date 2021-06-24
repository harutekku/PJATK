import Configuration.HibernateConfig;
import Models.*;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main{
	public static void main(String[] args){
		HibernateConfig.StartHibernateConfiguration();
		//addData();
		//PersonController personController=new PersonController();
		/*
		StandardServiceRegistry registry=null;
		SessionFactory sessionFactory=null;
		try{
			registry=new StandardServiceRegistryBuilder().configure().build();
			sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();

			Person user=Person.createUser("Andrzej","Jodłowski","727272727","bao@bao.pl","andrzejj","passXD");
			Person user2=Person.createUser("Marysia","Jodłowska","727272727","bao@bao.pl","marysia","passXD");
			session.save(user);
			session.save(user2);
			try{
				//Models.Person user3=Models.Person.createUser("Marysia","Mirowska","727272727","bao@bao.pl","marysia","passXD");
				//session.save(user3);
			}catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			CreditCard card=CreditCard.addCardToPerson(user,"12345",LocalDate.of(2023,12,22),"123");
			session.save(card);
			Company company=Company.createCompany("Mcdonald","Żywność");
			session.save(company);
			Restaurant restaurant=Restaurant.createRestaurant("Jerozolimskie",22,"01-111","Warszawa",company,0.30);
			GasStation gasStation=GasStation.createGasStation("Jerozolimskie",23,"01-112","Warszawa",company,0.20);
			Local local=Local.createLocal("Jerozolimskie",23,"01-112","Warszawa",company);
			session.save(restaurant);
			session.save(gasStation);
			session.save(local);

			//session.remove(card);
			//session.remove(user);


			*//*List<Models.Person> users=session.createQuery("from Models.Person",Models.Person.class).getResultList();
			for(Models.Person person:users){
				System.out.println(person);
				System.out.println(person.checkPersonKind(Models.PersonType.User));
				System.out.println(person.checkPersonKind(Models.PersonType.Editor));
//				for(Models.CreditCard card:person.getCreditCards()){
//					System.out.println(card);
//				}
			}*//*

			session.getTransaction().commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}finally{
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}*/
	}
	public static void addData(){
		Session session=HibernateConfig.openSession();
		session.beginTransaction();
		Company company=Company.createCompany("Mcdonald","Żywność");
		Restaurant restaurant=Restaurant.createRestaurant("Jerozolimskie",22,"01-111","Warszawa",company,0.30);
		Local local=Local.createLocal("Jerozolimskie",23,"01-112","Warszawa",company);
		Person user=Person.createUser("Stanisław","Badziak","775778553","badziak@gmail.com","badziak","P@ssw0rd");
		session.save(company);
		session.save(restaurant);
		session.save(local);
		session.save(user);
		try{
			CreditCard card=CreditCard.addCardToPerson(user,"12345",LocalDate.of(2023,12,22),"123");
			session.save(card);
		}catch(Exception e){
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.beginTransaction();
		Person editor=Person.createEditor("Marysia","Jodłowska","727272727","bao@bao.pl","marysia","passXD");
		session.getTransaction().commit();
		session.beginTransaction();
		Person operator=Person.createOperator("Andrzej","Kaminski","727272727","bao@bao.pl","andrzej","passXD");
		session.getTransaction().commit();
		session.beginTransaction();
		Offer offer1=new Offer(LocalDateTime.now(),LocalDateTime.now(),null,editor,restaurant);
		OfferItem offerItem1=new OfferItem("Frytki",new BigDecimal(5),offer1);
		OfferItem offerItem2=new OfferItem("Kanapka",new BigDecimal(7),offer1);
		OfferItem offerItem3=new OfferItem("Lody",new BigDecimal(5),offer1);

		Offer offer2=new Offer(LocalDateTime.now(),LocalDateTime.now(),null,editor,local);
		OfferItem offerItem4=new OfferItem("Cola",new BigDecimal(5),offer1);
		OfferItem offerItem5=new OfferItem("Pepsi",new BigDecimal(7),offer1);
		OfferItem offerItem6=new OfferItem("Chipsy",new BigDecimal(5),offer1);

		session.save(editor);
		session.getTransaction().commit();
		session.beginTransaction();
		session.save(operator);
		session.getTransaction().commit();
		session.beginTransaction();
		session.save(offer1);
		session.save(offerItem1);
		session.save(offerItem2);
		session.save(offerItem3);
		session.save(offer2);
		session.save(offerItem4);
		session.save(offerItem5);
		session.save(offerItem6);
		session.getTransaction().commit();
		session.close();
	}

}
