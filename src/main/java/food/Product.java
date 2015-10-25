package main.java.food;

import java.text.DecimalFormat;


// TODO add id field to product? To avoid all to db when inserting meal
public class Product implements Eatable
{
    private String name;
    private double protein;
    private double fat;
    private double carb;
    private double kcal;

    public Product(String name, double protein, double fat, double carb, double kcal)
    {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
        this.kcal = kcal;
    }

    /**
     * Constructs a new {@code Product} calculating its kcal/100 from an arbitrary weight.
     * @param weight should be in grams. Other values can be updated later.
     */

    public Product(String name, double kcal, double weight)
    {
        this.name = name;
        this.protein = 0;
        this.fat = 0;
        this.carb = 0;

        DecimalFormat df = new DecimalFormat("#.#");
        Double res = (kcal * 100) / weight;

        this.kcal = Double.parseDouble(df.format(res));
    }

    // TODO Is this really needed?
    @Override
    public double calculateKcal()
    {
        return 0;
    }

    public String getName()
    {
        return name;
    }

    public double getProtein()
    {
        return protein;
    }

    public double getFat()
    {
        return fat;
    }

    public double getCarb()
    {
        return carb;
    }

    public double getKcal()
    {
        return kcal;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
