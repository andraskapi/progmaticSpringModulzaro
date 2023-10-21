package hu.progmatic.progmaticspringmodulzaro.model;

import jakarta.persistence.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "shop_name")
    private String shopName;
    private String item;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer buyer;

    public Purchase(Integer id, String shopName, String item, Integer amount, Customer buyer) {
        this.id = id;
        this.shopName = shopName;
        this.item = item;
        this.amount = amount;
        this.buyer = buyer;
    }

    public Purchase() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Customer getBuyer() {
        return buyer;
    }

    public void setBuyer(Customer buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "" + amount;
    }
}
