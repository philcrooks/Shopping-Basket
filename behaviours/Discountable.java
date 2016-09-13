package behaviours;

public interface Discountable {

  abstract double applyDiscount(double preDiscountAmount);
  abstract Discountable duplicate();

}