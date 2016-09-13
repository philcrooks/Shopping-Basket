package shopping;

import java.util.*;
import behaviours.*;

public class ShoppingBasket {
  ArrayList<LineItem> lineItems;
  double totalPrice;
  BasketDiscount discount;
  Customer customer;

  public ShoppingBasket(BasketDiscount discount) {
    lineItems = new ArrayList<LineItem>();
    totalPrice = 0.0;
    customer = null;
  }

  public ShoppingBasket(Customer customer, BasketDiscount discount) {
    lineItems = new ArrayList<LineItem>();
    totalPrice = 0.0;
    this.customer = customer.duplicate();
  }

  public void addCustomer(Customer customer) {
    if (customer == null) this.customer = customer.duplicate();
  }

  public void addItem(Sellable item) {
    // Check to see if this product already exists in the basket.
    // If it does add one more to the line item
    // If not, create a new line item.

  }

  public void removeItem (Sellable item) {

  }

  public void empty() {
    if (lineItems != null) lineItems.clear();
  }

  private void totalize() {
    double total = 0.0;
    for (LineItem item : lineItems) {
      total += item.getPrice();
    }
    totalPrice = discount.applyDiscount(total);
    if (customer != null) totalPrice = customer.applyDiscount(totalPrice);
  }

  public double getPrice() {
    totalize();
    return totalPrice;
  }
}