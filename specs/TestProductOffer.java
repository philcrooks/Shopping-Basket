import static org.junit.Assert.*;
import org.junit.*;
import shopping.*;

public class TestProductOffer {
  ProductOffer bogof;
  ProductOffer perItem;
  ProductOffer buyTwoGetOneFree;

  @Before
  public void before(){
    bogof = new ProductOffer(1, 2, 50.0);
    perItem = new ProductOffer(2, 1, 10.0);
    buyTwoGetOneFree = new ProductOffer(3, 3, 33.33);
  }

  @Test
  public void testPerItemOffer(){
    assertEquals(45.00, perItem.priceOf(5, 10.0), 0.001);
  }

  @Test
  public void testBogof_OneItem(){
    assertEquals(5.0, bogof.priceOf(1, 5.00), 0.001);
  }

  @Test
  public void testBogof_EvenNumber(){
    assertEquals(10.0, bogof.priceOf(4, 5.00), 0.001);
  }

  @Test
  public void testBogof_OddNumber(){
    assertEquals(15.0, bogof.priceOf(5, 5.00), 0.001);
  }

  @Test
  public void testB2gof_OneItem(){
    assertEquals(5.0, buyTwoGetOneFree.priceOf(1, 5.00), 0.001);
  }

  @Test
  public void testB2gof_TwoItems(){
    assertEquals(10.0, buyTwoGetOneFree.priceOf(2, 5.00), 0.001);
  }

  @Test
  public void testB2gof_ThreeItems(){
    assertEquals(10.0, buyTwoGetOneFree.priceOf(3, 5.00), 0.001);
  }

  @Test
  public void testB2gof_FourItems(){
    assertEquals(15.0, buyTwoGetOneFree.priceOf(4, 5.00), 0.001);
  }
}