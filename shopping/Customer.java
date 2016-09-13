package shopping;
import behaviours.*;

public class Customer implements Discountable {

  private int id;
  private String name;
  private LoyaltyCard loyaltyCard;

  public Customer(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void addCard(LoyaltyCard card) {
    loyaltyCard = card.duplicate();
  }

  public double getDiscount () {
    if (loyaltyCard == null)
      return 0.0;
    else
      return loyaltyCard.getDiscount();
  }

}