public class Product
{
    private String name;
    private double proteins;
    private double lipids;
    private double carbs;
    private double kcal;

    public Product(String name, double proteins, double lipids, double carbs, double kcal)
    {
        this.name = name;
        this.proteins = proteins;
        this.lipids = lipids;
        this.carbs = carbs;
        this.kcal = kcal;
    }

    public String getName()
    {
        return name;
    }

    public double getProteins()
    {
        return proteins;
    }

    public double getLipids()
    {
        return lipids;
    }

    public double getCarbs()
    {
        return carbs;
    }

    public double getKcal()
    {
        return kcal;
    }
}
