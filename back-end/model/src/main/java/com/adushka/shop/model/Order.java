package com.adushka.shop.model;



import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book_order", schema = "book_shop")
@CsvEntity(filename = "data/csv/order.csv", id = "order")
public class Order implements IEntity, Serializable, Cloneable {
    private static final long serialVersionUID = 1242876949608763678L;

    private Integer id;
    //private Integer readerId;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 1)
    private Reader reader;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 2)
    private Status status;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
    private Date dateExecuted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
    private Integer price;
    @CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 5)
    private List<Book> books;


    public Order(){

    }

    public Order(Date dateExecuted, Status status, int price) {
        this.dateExecuted = dateExecuted;
        this.status = status;
        this.price = price;
    }

    public Order(Reader reader, Date dateExecuted, List<Book> books) {
        this.reader = reader;
        this.dateExecuted = dateExecuted;
        this.books = books;
        Date current = new Date();
        if (current.after(this.dateExecuted)) {
            this.status = Status.EXECUTED;
        }else{
            this.status = Status.AWAITING;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "readerId", nullable = false)
    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "dateExecuted")
    public Date getDateExecuted() {
        return dateExecuted;
    }

    public void setDateExecuted(Date dateExecuted) {
        this.dateExecuted = dateExecuted;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Book.class)
    @JoinTable(name = "order_history",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "bookId"))
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /*@Column(name = "readerId")
    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }*/

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return getId() + "," + status + "," + price + "," + df.format(dateExecuted);
    }

    private List<Book> cloneList(List<Book> list) throws CloneNotSupportedException {
        List<Book> clone = new ArrayList<>(list.size());
        for (Book item : list) clone.add(item.clone());
        return clone;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        Order clone = (Order) super.clone();
        clone.books = cloneList(books);
        clone.dateExecuted = (Date) dateExecuted.clone();
        clone.reader = reader.clone();
        return clone;
    }
}
