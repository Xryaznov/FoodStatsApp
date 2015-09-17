package main.java.food;

import java.text.DecimalFormat;

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
        final StringBuilder sb = new StringBuilder(name);
        sb.append(": ").append(protein);
        sb.append(", ").append(fat);
        sb.append(", ").append(carb);
        sb.append(", ").append(kcal);
        sb.append(";");
        return sb.toString();
    }
}
