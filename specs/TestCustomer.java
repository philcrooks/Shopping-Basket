import static org.junit.Assert.*;

import org.junit.*;

import basket.*;

public class TestCustomer {
  LoyaltyCard card;
  Customer customer;
  
  @Before
  public void before(){
    card = new LoyaltyCard(5, 2.0);
    customer = new Customer(4, "Phil");
  }

  @Test
  public void testDiscount_NoCard(){
    assertEquals(100.0, customer.applyDiscount(100.0), 0.001);
  }

  @Test
  public void testDiscount_WithCard(){
    customer.addCard(card);
    assertEquals(98.0, customer.applyDiscount(100.0), 0.001);
  }
}