package shopping;
import behaviours.*;

public class LoyaltyCard implements Discountable {

  private int id;
  private double discount;

  public LoyaltyCard(int id, double discount) {
    this.id = id;
    this.discount = discount;
  }

  public double getDiscount () {
    return discount;
  }
  
}