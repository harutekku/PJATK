package Controller;

import DAO.DAOGeneric;
import Models.OfferItem;

import java.util.List;

public class OfferItemController{
	private DAOGeneric<OfferItem,Long> OfferItemDAO=new DAOGeneric<>(OfferItem.class);
	public List<OfferItem> getAll(){
		return OfferItemDAO.getAll();
	}
	public OfferItem get(long id){
		return OfferItemDAO.get(id);
	}
	public OfferItem update(OfferItem offerItem){
		return OfferItemDAO.update(offerItem);
	}
	public void add(OfferItem offerItem){
		OfferItemDAO.add(offerItem);
	}
	public void remove(OfferItem offerItem){
		OfferItemDAO.remove(offerItem);
	}
}
