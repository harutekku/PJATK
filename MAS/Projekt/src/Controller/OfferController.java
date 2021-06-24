package Controller;

import DAO.DAOGeneric;
import Models.Offer;

import java.util.List;

/**
 * The type Offer controller.
 */
public class OfferController{
	private DAOGeneric<Offer,Long> OfferDAO=new DAOGeneric<>(Offer.class);
	/**
	 * Get all list.
	 *
	 * @return the list
	 */
	public List<Offer> getAll(){
		return OfferDAO.getAll();
	}
	/**
	 * Get offer.
	 *
	 * @param id the id
	 * @return the offer
	 */
	public Offer get(long id){
		return OfferDAO.get(id);
	}
	/**
	 * Update offer.
	 *
	 * @param offer the offer
	 * @return the offer
	 */
	public Offer update(Offer offer){
		return OfferDAO.update(offer);
	}
	/**
	 * Add.
	 *
	 * @param offer the offer
	 */
	public void add(Offer offer){
		OfferDAO.add(offer);
	}
	/**
	 * Remove.
	 *
	 * @param offer the offer
	 */
	public void remove(Offer offer){
		OfferDAO.remove(offer);
	}
}
