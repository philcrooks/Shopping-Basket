package shopping;

import behaviours.*;

public class Product implements Sellable {
  private int id;
  private String name;
  private double price;
  private ProductOffer offer;

  public Product(int id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
    offer = null;
  }

  private Product(Product another) {
    this.id = another.id;
    this.name = another.name;
    this.price = another.price;
    this.offer = another.offer;
    if (another.offer != null) this.offer = this.offer.duplicate();
  }

  public Product duplicate() {
    return new Product(this);
  }

  public void addSpecialOffer(ProductOffer offer) {
    this.offer = offer.duplicate();
  }

  public int getId() {
    return id;
  }

  public double priceOf(int noItems) {
    if (offer == null)
      return noItems * price;
    else
      return offer.priceOf(noItems, price);
  }
  
}
