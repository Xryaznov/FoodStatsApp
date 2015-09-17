package test.java.product;

import main.java.food.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest
{

    Product product1;
    Product product2;

    @Before
    public void setUp() throws Exception
    {
        product1 = new Product("ЙогуртВолошПоле", 2.5, 2.8, 19.6, 113.6);
        product2 = new Product("ФитнессБатNestle", 87, 23.5);

    }

    @Test
    public void testGetKcal() throws Exception
    {
        assertEquals(113.6, product1.getKcal(), 0.1);
        assertEquals(370.2, product2.getKcal(), 0.1);
    }

    @Test
    public void testToString() throws Exception
    {
        assertEquals("ЙогуртВолошПоле: 2.5, 2.8, 19.6, 113.6;", product1.toString());
        assertEquals("ФитнессБатNestle: 0.0, 0.0, 0.0, 370.2;", product2.toString());
    }
}