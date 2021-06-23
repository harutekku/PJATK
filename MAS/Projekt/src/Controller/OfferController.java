package Controller;

import DAO.DAOGeneric;
import Models.Offer;

import java.util.List;

public class OfferController{
	private DAOGeneric<Offer,Long> OfferDAO=new DAOGeneric<>(Offer.class);
	public List<Offer> getAll(){
		return OfferDAO.getAll();
	}
	public Offer get(long id){
		return OfferDAO.get(id);
	}
	public Offer update(Offer offer){
		return OfferDAO.update(offer);
	}
	public void add(Offer offer){
		OfferDAO.add(offer);
	}
	public void remove(Offer offer){
		OfferDAO.remove(offer);
	}
}
