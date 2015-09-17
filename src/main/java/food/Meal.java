package main.java.food;


import main.java.utils.Utils;

public class Meal implements Eatable
{
    private String date;
    private Product product;
    private double weight;

    public Meal(String date, Product product, double weight)
    {
        this.date = date;
        this.product = product;
        this.weight = weight;
    }

    @Override
    public double calculateKcal()
    {
        return product.getKcal() * weight / 100;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(Utils.getCurrentDay());
        sb.append(": ").append(product.getName()).append(", ");
        sb.append(calculateKcal()).append(";");
        return sb.toString();
    }

    public Product getProduct()
    {
        return product;
    }
}
