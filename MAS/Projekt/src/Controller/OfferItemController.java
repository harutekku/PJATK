package Controller;

import DAO.DAOGeneric;
import Models.OfferItem;

import java.util.List;

/**
 * The type Offer item controller.
 */
public class OfferItemController{
	private DAOGeneric<OfferItem,Long> OfferItemDAO=new DAOGeneric<>(OfferItem.class);
	/**
	 * Get all list.
	 *
	 * @return the list
	 */
	public List<OfferItem> getAll(){
		return OfferItemDAO.getAll();
	}
	/**
	 * Get offer item.
	 *
	 * @param id the id
	 * @return the offer item
	 */
	public OfferItem get(long id){
		return OfferItemDAO.get(id);
	}
	/**
	 * Update offer item.
	 *
	 * @param offerItem the offer item
	 * @return the offer item
	 */
	public OfferItem update(OfferItem offerItem){
		return OfferItemDAO.update(offerItem);
	}
	/**
	 * Add.
	 *
	 * @param offerItem the offer item
	 */
	public void add(OfferItem offerItem){
		OfferItemDAO.add(offerItem);
	}
	/**
	 * Remove.
	 *
	 * @param offerItem the offer item
	 */
	public void remove(OfferItem offerItem){
		OfferItemDAO.remove(offerItem);
	}
}
