package behaviours;

public interface Sellable {

  abstract double priceOf(int numberOf);
  abstract int getId();
  abstract Sellable duplicate();
}