import static org.junit.Assert.*;
import org.junit.*;
import basket.*;

public class TestLineItem {
  ProductOffer offer;
  Product productWithOffer;
  Product productWithoutOffer;
  LineItem lineItem;
  
  @Before
  public void before(){
    offer = new ProductOffer(10, 3, 33.33);
    productWithOffer = new Product(16, "Stripey Socks", 5.0);
    productWithOffer.addSpecialOffer(offer);
    productWithoutOffer = new Product(33, "Plain Black Socks", 3.5);
  }

  @Test
  public void testContains_True() {
    lineItem = new LineItem(productWithoutOffer);
    assertEquals(true, lineItem.contains(productWithoutOffer));
  }

  @Test
  public void testContains_False() {
    lineItem = new LineItem(productWithoutOffer);
    assertEquals(false, lineItem.contains(productWithOffer));
  }

  @Test
  public void testPrice() {
    lineItem = new LineItem(productWithoutOffer);
    assertEquals(3.5, lineItem.getPrice(), 0.001);
  }

  @Test
  public void testAddItem() {
    lineItem = new LineItem(productWithoutOffer);
    lineItem.addItem();
    assertEquals(7.0, lineItem.getPrice(), 0.001);
  }

  @Test
  public void testDiscount_NoDiscount() {
    lineItem = new LineItem(productWithoutOffer);
    lineItem.setNumberOfItems(3);
    productWithoutOffer.addSpecialOffer(offer);
    assertEquals(10.5, lineItem.getPrice(), 0.001);
  }

  @Test
  public void testDiscount_WithDiscount() {
    lineItem = new LineItem(productWithOffer);
    lineItem.setNumberOfItems(3);
    assertEquals(10, lineItem.getPrice(), 0.001);
  }

  @Test
  public void testRemoveItem() {
    lineItem = new LineItem(productWithoutOffer);
    lineItem.setNumberOfItems(3);
    lineItem.removeItem();
    assertEquals(7, lineItem.getPrice(), 0.001);
  }

  // @Test
  // public void testRemoveAllItems() {
  //   lineItem = new LineItem(productWithoutOffer);
  //   lineItem.setNumberOfItems(3);
  //   lineItem.removeAllItems();
  //   assertEquals(0, lineItem.getPrice(), 0.001);
  // }
}