import static org.junit.Assert.*;
import org.junit.*;
import shopping.*;

public class TestProduct {
  ProductOffer offer;
  Product product;
  
  @Before
  public void before(){
    offer = new ProductOffer(10, 3, 33.33);
    product = new Product(16, "Stripey Socks", 5.0);
  }

  @Test
  public void testPrice_NoOffer() {
    assertEquals(15.0, product.priceOf(3), 0.001);
  }

  @Test
  public void testPrice_WithOffer(){
    product.addSpecialOffer(offer);
    assertEquals(10.0, product.priceOf(3), 0.001);
  }
}