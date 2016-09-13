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

  public double costOf(int noItems, double priceEach) {
    int discountApplyNumber = (noItems/this.noItems) * this.noItems;
    int discountDoesNotApplyNumber = noItems - discountApplyNumber;
    double cost = (discountApplyNumber * priceEach);
    cost -= (cost * discount) / 100;
    cost += discountDoesNotApplyNumber * priceEach;
    return cost;
  }
  
}