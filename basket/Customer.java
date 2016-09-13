package basket;

import behaviours.*;

public class Customer implements Discountable {

  private int id;
  private String name;
  private LoyaltyCard loyaltyCard;

  public Customer(int id, String name) {
    this.id = id;
    this.name = name;
  }

  private Customer(Customer another) {
    this.id = another.id;
    this.name = another.name;
    this.loyaltyCard = null;
    if (another.loyaltyCard != null) this.loyaltyCard = another.loyaltyCard.duplicate();
  }

  public Customer duplicate() {
    return new Customer(this);
  }

  public void addCard(LoyaltyCard card) {
    loyaltyCard = card.duplicate();
  }

  public double applyDiscount (double amount) {
    if (loyaltyCard == null)
      return amount;
    else
      return loyaltyCard.applyDiscount(amount);
  }

}