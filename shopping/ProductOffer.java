package shopping;

import behaviours.*;

public class ProductOffer {
  private int id;
  private int noItems;
  private double discount;

  public ProductOffer(int id, int noItems, double discount) {
    this.id = id;
    this.noItems = noItems;
    this.discount = discount;
  }

  private ProductOffer(ProductOffer another) {
    this.id = another.id;
    this.noItems = another.noItems;
    this.discount = another.discount;
  }

  public ProductOffer duplicate() {
    return new ProductOffer(this);
  }

  public double priceOf(int noItems, double priceEach) {
    int discountApplyNumber = (noItems/this.noItems) * this.noItems;
    int discountDoesNotApplyNumber = noItems - discountApplyNumber;
    double cost = (discountApplyNumber * priceEach);
    cost -= (cost * discount) / 100;
    cost += discountDoesNotApplyNumber * priceEach;
    return cost;
  }
  
}