import static org.junit.Assert.*;
import org.junit.*;
import basket.*;

public class TestLoyaltyCard {
  LoyaltyCard card;
  
  @Before
  public void before(){
    card = new LoyaltyCard(5, 3.0);
  }

  @Test
  public void testDiscount(){
    assertEquals(97.0, card.applyDiscount(100.0), 0.001);
  }
}