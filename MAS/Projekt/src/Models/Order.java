package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

enum Status{pending,progress,finished,cancelled}

@Entity(name="Order_fix")
public class Order{
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

	@Basic
	public LocalDateTime getCreationDate(){
		return creationDate;
	}
	private void setCreationDate(LocalDateTime creationDate){
		this.creationDate=creationDate;
	}
	private LocalDateTime creationDate;

	@Basic
	public LocalDateTime getRealizationDate(){
		return realizationDate;
	}
	private void setRealizationDate(LocalDateTime realizationDate){
		this.realizationDate=realizationDate;
	}
	private LocalDateTime realizationDate;

	@Enumerated(EnumType.STRING)
	public Status getStatus(){
		return status;
	}
	private void setStatus(Status status){
		this.status=status;
	}
	private Status status;

	@Column(columnDefinition="DECIMAL")
	public BigDecimal getAmount(){
		return amount;
	}
	private void setAmount(BigDecimal amount){
		this.amount=amount;
	}
	private BigDecimal amount;

	@ManyToOne
	public Person getUser(){
		return user;
	}
	public void setUser(Person user){
		this.user=user;
	}
	private Person user;

	@ManyToOne
	public Person getProducer(){
		return producer;
	}
	public void setProducer(Person producer){
		this.producer=producer;
	}
	private Person producer;

	@ManyToOne
	public Person getOperator(){
		return operator;
	}
	public void setOperator(Person operator){
		this.operator=operator;
	}
	private Person operator;

	@ManyToOne
	public Local getLocal(){
		return local;
	}
	public void setLocal(Local local){
		this.local=local;
	}
	private Local local;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
			name="OrderList",
			joinColumns = @JoinColumn(name = "Order_id"),
			inverseJoinColumns = @JoinColumn(name = "OfferItem_id"))
	public Set<OfferItem> getOrderList(){
		return orderList;
	}
	public void setOrderList(Set<OfferItem> orderList){
		this.orderList=orderList;
	}
	private Set<OfferItem> orderList=new HashSet<OfferItem>();


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
	public static Order createOrder(Person user,Local local,BigDecimal amount){
		Order order=new Order();
		order.setLocal(local);
		order.setUser(user);
		order.setStatus(Status.pending);
		order.setCreationDate(LocalDateTime.now());
		order.setAmount(amount);
		return order;
	}

}
