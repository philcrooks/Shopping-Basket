package basket;

import java.util.*;
import behaviours.*;

public class ShoppingBasket {
  ArrayList<LineItem> lineItems;
  double totalPrice;
  Discountable discount;
  Customer customer;

  public ShoppingBasket(Discountable discount) {
    this.discount = discount.duplicate();
    lineItems = new ArrayList<LineItem>();
    totalPrice = 0.0;
    customer = null;
  }

  public ShoppingBasket(Customer customer, Discountable discount) {
    this.customer = customer.duplicate();
    this.discount = discount.duplicate();
    lineItems = new ArrayList<LineItem>();
    totalPrice = 0.0;
  }

  public void addCustomer(Customer customer) {
    if (this.customer == null) {
      this.customer = customer.duplicate();
    }
  }

  public int getIndex(Sellable item) {
    LineItem li;
    for (int i = 0; i < lineItems.size(); i++) {
      li = lineItems.get(i);
      if (li.contains(item)) return i;
    }
    return -1;
  }

  public void addItem(Sellable item) {
    // Check to see if this product already exists in the basket.
    for (LineItem i : lineItems) {
      if (i.contains(item)) {
        // Found it - add one more to the line item
        i.addItem();
        return;
      }
    }
    lineItems.add(new LineItem(item));
  }

  public void addItem(int itemIndex) {
    lineItems.get(itemIndex).addItem();
  }

  public void setNumberOfItems(int itemIndex, int numberOfItems) {
    lineItems.get(itemIndex).setNumberOfItems(numberOfItems);  
  }

  // public void removeItem(Sellable item) {
  //   LineItem li;
  //   for (int i = 0; i < lineItems.size(); i++) {
  //     li = lineItems.get(i);
  //     if (li.contains(item)) {
  //       li.removeItem();
  //       if (li.isEmpty()) lineItems.remove(i);
  //       return;
  //     }
  //   }
  // }

  public void removeItem(int itemIndex) {
    LineItem li = lineItems.get(itemIndex);
    li.removeItem();
    if (li.isEmpty()) lineItems.remove(itemIndex);
  }

  public void clearItem(int itemIndex) {
    lineItems.remove(itemIndex);
  }

  public void clearBasket() {
    if (lineItems != null) lineItems.clear();
  }

  private void totalize() {
    double total = 0.0;
    for (LineItem item : lineItems) {
      total += item.getPrice();
    }
    // Any product-specific discounts are applied within the line-item
    // Apply any shopping basket specific discounts now
    totalPrice = discount.applyDiscount(total);
    // Customer discount applied last
    if (customer != null) {
      totalPrice = customer.applyDiscount(totalPrice);
    }
  }

  public double getPrice() {
    totalize();
    return totalPrice;
  }
}