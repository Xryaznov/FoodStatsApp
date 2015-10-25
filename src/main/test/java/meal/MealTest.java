package test.java.meal;

import main.java.food.Meal;
import main.java.food.Product;
import main.java.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MealTest
{
    Product product;
    Meal meal;

    @Before
    public void setUp() throws Exception
    {
        product = new Product("ЙогуртВолошПоле", 2.5, 2.8, 19.6, 113.6);
        meal = new Meal(Utils.getCurrentDay(), product, 350);

    }

    @Test
    public void testCalculateKcal() throws Exception
    {
        assertEquals(113.6 * 3.5, meal.calculateKcal(), 0.01);
    }

    @Test
    public void testToString() throws Exception
    {
        assertEquals(Utils.getCurrentDay() + ": ЙогуртВолошПоле, 397.6;", meal.toString());
    }
}