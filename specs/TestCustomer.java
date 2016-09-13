import static org.junit.Assert.*;
import org.junit.*;
import shopping.*;

public class TestCustomer {
  LoyaltyCard card;
  Customer customer;
  
  @Before
  public void before(){
    card = new LoyaltyCard(5, 3.0);
    customer = new Customer(4, "Phil");
  }

  @Test
  public void testDiscount_NoCard(){
    assertEquals(0.0, customer.getDiscount(), 0.001);
  }

  @Test
  public void testDiscount_WithCard(){
    customer.addCard(card);
    assertEquals(3.0, customer.getDiscount(), 0.001);
  }
}