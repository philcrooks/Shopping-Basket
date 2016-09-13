package shopping;

import java.util.*;

public class ShoppingBasket {
  ArrayList<LineItem> lineItems;
  double totalCost;

  public ShoppingBasket() {
    lineItems = new ArrayList<LineItem>();
    totalCost = 0.0;
  }

  public void add(Product product) {
    // Check to see if this product already exists in the basket.
    // If it does add one more to the line item
    // If not, create a new line item.

  }

  public void remove (Product product) {

  }

  public void empty() {
    if (lineItems != null) lineItems.clear();
  }

  public void totalize() {
    double total = 0.0;
    for (LineItem item : lineItems) {
      total += item.cost();
    }
    totalCost = total;
  }
}