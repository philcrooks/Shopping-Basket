package basket;

import behaviours.*;

public class BasketDiscount implements Discountable {

  private double discount;
  private double discountThreshold;

  public BasketDiscount(double discount, double discountThreshold) {
    this.discount = discount;
    this.discountThreshold = discountThreshold;
  }

  private BasketDiscount(BasketDiscount another) {
    this.discount = another.discount;
    this.discountThreshold = another.discountThreshold;
  }

  public BasketDiscount duplicate() {
    return new BasketDiscount(this);
  }

  public double applyDiscount (double amount) {
    if (amount > discountThreshold)
      return amount - ((amount * discount) / 100);
    return amount;
  }
}