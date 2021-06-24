package Models;

import Controller.OrderItemController;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The enum Status.
 */
enum Status{
	/**
	 * Pending status.
	 */
	pending,
	/**
	 * Progress status.
	 */
	progress,
	/**
	 * Finished status.
	 */
	finished,
	/**
	 * Cancelled status.
	 */
	cancelled}

/**
 * The type Order.
 */
@Entity(name="Order_fix")
public class Order{
	/**
	 * Get id long.
	 *
	 * @return the long
	 */
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	public long getId(){
		return id;
	}
	private void setId(long id){
		this.id=id;
	}
	private long id;

	/**
	 * Get creation date local date time.
	 *
	 * @return the local date time
	 */
	@Basic
	public LocalDateTime getCreationDate(){
		return creationDate;
	}
	private void setCreationDate(LocalDateTime creationDate){
		this.creationDate=creationDate;
	}
	private LocalDateTime creationDate;

	/**
	 * Get realization date local date time.
	 *
	 * @return the local date time
	 */
	@Basic
	public LocalDateTime getRealizationDate(){
		return realizationDate;
	}
	private void setRealizationDate(LocalDateTime realizationDate){
		this.realizationDate=realizationDate;
	}
	private LocalDateTime realizationDate;

	/**
	 * Get status status.
	 *
	 * @return the status
	 */
	@Enumerated(EnumType.STRING)
	public Status getStatus(){
		return status;
	}
	private void setStatus(Status status){
		this.status=status;
	}
	private Status status;

	/**
	 * Get amount big decimal.
	 *
	 * @return the big decimal
	 */
	@Column(columnDefinition="DECIMAL")
	public BigDecimal getAmount(){
		return amount;
	}
	private void setAmount(BigDecimal amount){
		this.amount=amount;
	}
	private BigDecimal amount;

	/**
	 * Get user person.
	 *
	 * @return the person
	 */
	@ManyToOne
	public Person getUser(){
		return user;
	}
	/**
	 * Set user.
	 *
	 * @param user the user
	 */
	public void setUser(Person user){
		this.user=user;
	}
	private Person user;

	/**
	 * Get producer person.
	 *
	 * @return the person
	 */
	@ManyToOne
	public Person getProducer(){
		return producer;
	}
	/**
	 * Set producer.
	 *
	 * @param producer the producer
	 */
	public void setProducer(Person producer){
		this.producer=producer;
	}
	private Person producer;

	/**
	 * Get operator person.
	 *
	 * @return the person
	 */
	@ManyToOne
	public Person getOperator(){
		return operator;
	}
	/**
	 * Set operator.
	 *
	 * @param operator the operator
	 */
	public void setOperator(Person operator){
		this.operator=operator;
	}
	private Person operator;

	/**
	 * Get local local.
	 *
	 * @return the local
	 */
	@ManyToOne
	public Local getLocal(){
		return local;
	}
	/**
	 * Set local.
	 *
	 * @param local the local
	 */
	public void setLocal(Local local){
		this.local=local;
	}
	private Local local;

	/*@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
			name="OrderList",
			joinColumns = @JoinColumn(name = "Order_id"),
			inverseJoinColumns = @JoinColumn(name = "OfferItem_id"))
	public Set<OfferItem> getOfferItemsList(){
		return offerItemsList;
	}
	public void setOfferItemsList(Set<OfferItem> offerItemsList){
		this.offerItemsList=offerItemsList;
	}
	private Set<OfferItem> offerItemsList=new HashSet<OfferItem>();*/

	/**
	 * Get order list list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
	public List<OrderList> getOrderList(){
		return orderList;
	}
	private void setOrderList(List<OrderList> orderList){
		this.orderList=orderList;
	}
	/**
	 * Add order list.
	 *
	 * @param orderList the order list
	 * @throws Exception the exception
	 */
	public void addOrderList(OrderList orderList) throws Exception{
		if(orderList.getOrder().equals(this)){
			this.orderList.add(orderList);
		}else{
			throw new Exception("This orderList is not created for this order");
		}
	}
	private List<OrderList> orderList=new ArrayList<>();

	/**
	 * Get reviews list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="local", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Review> getReviews(){
		return reviews;
	}
	private void setReviews(List<Review> reviews){
		this.reviews=reviews;
	}
	/**
	 * Add review.
	 *
	 * @param review the review
	 * @throws Exception the exception
	 */
	public void addReview(Review review) throws Exception{
		if(review.getLocal().equals(this)){
			reviews.add(review);
		}else{
			throw new Exception("Models.Review is not created for this local");
		}
	}
	private List<Review> reviews=new ArrayList<>();


	/**
	 * Instantiates a new Order.
	 */
	protected Order(){}
	private Order(LocalDateTime creationDate,LocalDateTime realizationDate,Status status,BigDecimal amount,Person user,Person producer,Person operator,Local local){
		this.creationDate=creationDate;
		this.realizationDate=realizationDate;
		this.status=status;
		this.amount=amount;
		this.user=user;
		this.producer=producer;
		this.operator=operator;
		this.local=local;
	}
	/**
	 * Create order order.
	 *
	 * @param user   the user
	 * @param local  the local
	 * @param amount the amount
	 * @return the order
	 */
	public static Order createOrder(Person user,Local local,BigDecimal amount){
		Order order=new Order();
		order.setLocal(local);
		order.setUser(user);
		order.setStatus(Status.pending);
		order.setCreationDate(LocalDateTime.now());
		order.setAmount(amount);
		return order;
	}
	/**
	 * Create order order.
	 *
	 * @param user   the user
	 * @param local  the local
	 * @param amount the amount
	 * @param items  the items
	 * @return the order
	 * @throws Exception the exception
	 */
	public static Order createOrder(Person user,Local local,BigDecimal amount,List<OfferItem> items) throws Exception{
		Order order=new Order();
		order.setLocal(local);
		order.setUser(user);
		order.setStatus(Status.pending);
		order.setCreationDate(LocalDateTime.now());
		order.setAmount(amount);
		for(OfferItem item:items){
			OrderList var = OrderList.createOrderList(order,item);
			if(order.getOrderList().contains(var)){
				int index=order.getOrderList().indexOf(var);
				order.getOrderList().get(index).setCount(order.getOrderList().get(index).getCount()+1);
			}
			else{
				order.addOrderList(var);
			/*OrderItemController orderItemController=new OrderItemController();
			List<OrderList> orderList=orderItemController.getAll();*/
			//for(OrderList item:)
			//if(order.getOrderList().contains(item)){
				//order.getOrderList().indexOf(item)
			}
			//OrderList orderList=new OrderList(item)
		}
		//order.setOrderList(items);

		for(OrderList item:order.getOrderList()){
			System.out.println(item.getCount()+" "+item.getOfferItem());
		}
		return order;
	}

}
