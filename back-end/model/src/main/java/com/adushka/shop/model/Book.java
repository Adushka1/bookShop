package com.adushka.shop.model;



import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "book", schema = "book_shop")
@CsvEntity(filename = "data/csv/book.csv", id = "book")
public class Book implements IEntity, Serializable, Cloneable {
    private static final long serialVersionUID = 5271189679230904618L;

    private Integer id;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
    private String title;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
    private Integer price;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
    private Boolean isStore;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
    private Date dateReceipted;
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
    private Date datePublished;


    public Book(){

    }

    public Book(String title, int price, boolean isStore, Date datePublished, Date dateReceipted) {
        this.title = title;
        this.price = price;
        this.isStore = isStore;
        this.datePublished = datePublished;
        this.dateReceipted = dateReceipted;
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

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "isStore")
    public Boolean getIsStore() {
        return isStore;
    }

    public void setIsStore(Boolean store) {
        isStore = store;
    }

    @Basic
    @Column(name = "dateReceipted")
    public Date getDateReceipted() {
        return dateReceipted;
    }

    public void setDateReceipted(Date dateReceipted) {
        this.dateReceipted = dateReceipted;
    }

    @Basic
    @Column(name = "datePublished")
    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return title + "," + getId() + "," + price + "," + isStore + "," + df.format(datePublished) +
                "," + df.format(dateReceipted);
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book that = (Book) o;

        if (!id.equals(that.id)) return false;
        if (isStore != that.isStore) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (dateReceipted != null ? !dateReceipted.equals(that.dateReceipted) : that.dateReceipted != null)
            return false;
        if (datePublished != null ? !datePublished.equals(that.datePublished) : that.datePublished != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isStore ? 1 : 0);
        result = 31 * result + (dateReceipted != null ? dateReceipted.hashCode() : 0);
        result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
