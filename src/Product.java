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

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(name).append(':');
        sb.append(", PROTEIN: ").append(proteins);
        sb.append(", FAT: ").append(lipids);
        sb.append(", CARB: ").append(carbs);
        sb.append(", KCAL: ").append(kcal);
        return sb.toString();
    }
}
