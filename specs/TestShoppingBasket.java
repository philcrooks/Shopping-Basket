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
    basket.addItem(product1);
    assertEquals(5.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBogof() {
    basket.addItem(product1);
    basket.addItem(product1);
    assertEquals(5.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_NoDiscount() {
    basket.addItem(product1);
    basket.addItem(product2);
    assertEquals(20.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_BogofNoDiscount() {
    basket.addItem(product1);
    basket.addItem(product1);
    basket.addItem(product2);
    assertEquals(20.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_BogofWithDiscount() {
    basket.addItem(product1);
    basket.addItem(product1);
    basket.addItem(product1);
    basket.addItem(product2);
    assertEquals(22.5, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketDiscount_NoBogofWithDiscount() {
    basket.addItem(product2);
    basket.addItem(product2);
    assertEquals(27.0, basket.getPrice(), 0.001);
  }

  @Test
  public void testBasketAndCustomerDiscount() {
    basket.addItem(product2);
    basket.addItem(product2);
    basket.addCustomer(customer);
    assertEquals(26.46, basket.getPrice(), 0.001);
  }
}