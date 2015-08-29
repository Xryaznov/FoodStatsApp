public class Product
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
        sb.append(", PROTEIN: ").append(protein);
        sb.append(", FAT: ").append(fat);
        sb.append(", CARB: ").append(carb);
        sb.append(", KCAL: ").append(kcal);
        return sb.toString();
    }
}
