import static org.junit.Assert.*;
import org.junit.*;
import basket.*;

public class TestShoppingBasket {
  Customer customer;
  Product product1;
  Product product2;
  ShoppingBasket basket;

  @Before
  public void before(){
    // 2% discount for loyalty cards
    LoyaltyCard card = new LoyaltyCard(1, 2.0);
    customer = new Customer(2, "Phil");
    customer.addCard(card);

    // Create a bogof offer - 50% discount when you buy two items
    ProductOffer offer = new ProductOffer(3, 2, 50.0);
    product1 = new Product(4, "Product 1", 5.0);
    product1.addSpecialOffer(offer);

    product2 = new Product(5, "Product 2", 15.0);

    // 10% discount after £20 spent
    BasketDiscount discount = new BasketDiscount(10.0, 20.0);
    basket = new ShoppingBasket(discount);
  }

  @Test
  public void testLoyaltyCard() {
    assertEquals(26.46, customer.applyDiscount(27.0), 0.001);
  }

  @Test
  public void testEmptyBasket() {
    assertEquals(0.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testAddItem() {
    // A customer clicks "buy now" on an item on the website
    basket.addItem(product1);
    assertEquals(5.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBogof() {
    // A customer clicks "buy now" twice on an item that has a bogof offer
    basket.addItem(product1);
    basket.addItem(product1);
    assertEquals(5.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_NoDiscount() {
    // A customer clicks "buy now" twice on two items that have no special offers
    // Note that the 10% discount does not come into effect until more than £20 is spent
    basket.addItem(product1);
    basket.addItem(product2);
    assertEquals(20.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_BogofNoDiscount() {
    // A customer buys two of one item and one of another. A bogof offer comes into effect.
    // There is no "basket discount" because it does not come into effect until *more than* £20 is spent.
    basket.addItem(product1);
    basket.addItem(product1);
    basket.addItem(product2);
    assertEquals(20.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_BogofWithDiscount() {
    // A customer buys three of one item and one of another. A bogof offer comes into effect on one of the items.
    // There is a "basket discount" of 10% because more than £20 is spent (£25 is spent).
    basket.addItem(product1);
    basket.addItem(product1);
    basket.addItem(product1);
    basket.addItem(product2);
    assertEquals(22.5, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_NoBogofWithDiscount() {
    // There is a "basket discount" of 10% because more than £20 is spent (£30 is spent).
    // There is no bogof offer
    basket.addItem(product2);
    basket.addItem(product2);
    assertEquals(27.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketAndCustomerDiscount() {
    // There is a "basket discount" of 10% because more than £20 is spent (£30 is spent).
    // THe customer has a loyalty card so there is a further discount of 2% applied after the basket discount.
    basket.addItem(product2);
    basket.addItem(product2);
    // Adding the customer to the basket after buying the items is the equivalent of logging into the system after shopping has started.
    basket.addCustomer(customer);
    assertEquals(26.46, basket.getPrice(), 0.001);
  }

  @Test
  public void testGetIndex() {
    // Using the index of a line item to update the basket is the equivalent of interactining with the basket itself
    // (rather than clicking on "buy now" elsewhere on the website).
    // Check that the index can be found.
    basket.addItem(product2);
    basket.addItem(product1);
    assertEquals(0, basket.getIndex(product2));
    assertEquals(1, basket.getIndex(product1));
  }

  @Test
  public void testRemoveByIndex() {
    // Test that the user can reduce the number of items he wants by one
    // Because there is only one item on the line, removing the item removes the line
    basket.addItem(product2);
    basket.addItem(product1);
    basket.removeItem(basket.getIndex(product2));
    assertEquals(5.0, basket.getPrice(), 0.001);
    assertEquals(1, basket.size()); // Only one line item left
  }

  @Test
  public void testAddByIndex() {
    // Test that the user can add one to the number of items he wants
    // (by interacting with the basket rather than clicking on "buy now")
    basket.addItem(product2);
    basket.addItem(product1);
    basket.addItem(basket.getIndex(product2));
    assertEquals(31.5, basket.getPrice(), 0.001);
    assertEquals(2, basket.size());
  }

  @Test
  public void testSetNumberOfItems() {
    // Test that the user can arbitrarily change the number of items he wants
    // (by interacting with the basket rather than clicking on "buy now")
    basket.addItem(product1);
    basket.setNumberOfItems(basket.getIndex(product1), 10);
    assertEquals(22.5, basket.getPrice(), 0.001);
    assertEquals(1, basket.size());
  }

  @Test
  public void testClearBasket() {
    // Test that the user can empty the basket
    basket.addItem(product1);
    basket.setNumberOfItems(basket.getIndex(product1), 10);
    basket.clearBasket();
    // No line items and no price
    assertEquals(0.0, basket.getPrice(), 0.001);
    assertEquals(0, basket.size());
  }
}