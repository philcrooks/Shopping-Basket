package shopping;
import behaviours.*;

public class LoyaltyCard implements Discountable {

  private int id;
  private double discount;

  public LoyaltyCard(int id, double discount) {
    this.id = id;
    this.discount = discount;
  }

  private LoyaltyCard(LoyaltyCard another) {
    this.id = another.id;
    this.discount = another.discount;
  }

  public LoyaltyCard duplicate() {
    return new LoyaltyCard(this);
  }

  public double getDiscount () {
    return discount;
  }
  
}