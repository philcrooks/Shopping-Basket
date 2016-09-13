package basket;

import behaviours.*;

public class LineItem {
  private Sellable item;
  private int numberOf;
  private double linePrice;

  public LineItem(Sellable item) {
    this.item = item.duplicate();
    numberOf = 1;
    linePrice = this.item.priceOf(numberOf);
  }

  public boolean contains(Sellable item) {
    return (this.item.getId() == item.getId());
  }

  public void addItem() {
    numberOf += 1;
    linePrice = item.priceOf(numberOf);
  }

  public void removeItem() {
    numberOf -= 1;
    if (numberOf < 0) numberOf = 0;
    linePrice = item.priceOf(numberOf);
  }

  // public void removeAllItems() {
  //   numberOf = 0;
  //   linePrice = 0.0;
  // }

  public void setNumberOfItems(int noItems) {
    if (noItems >= 0) numberOf = noItems;
    linePrice = item.priceOf(numberOf);
  }

  public double getPrice() {
    return linePrice;
  }

  public boolean isEmpty() {
    return (numberOf == 0);
  }
}