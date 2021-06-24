import Configuration.HibernateConfig;
import Models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;

public class GUI extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		try{
			java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
			HibernateConfig.StartHibernateConfiguration();
			//addData();
			Parent root =FXMLLoader.load(getClass().getResource("GUI/MainMenu.fxml"));
			Scene scene=new Scene(root);
			primaryStage.setTitle("Zamawianko");
			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		launch(args);
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
		OfferItem offerItem4=new OfferItem("Cola",new BigDecimal(5),offer2);
		OfferItem offerItem5=new OfferItem("Pepsi",new BigDecimal(7),offer2);
		OfferItem offerItem6=new OfferItem("Chipsy",new BigDecimal(5),offer2);

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
