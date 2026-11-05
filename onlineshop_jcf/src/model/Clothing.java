package model;
public class Clothing extends Product implements Discountable {
    private String size;
    private String material;

    public Clothing(int id, String name, double price,
                    String category, String size, String material) {
        super(id, name, price, category);
        this.size = size;
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public double applyDiscount() {
        return getPrice() * 0.90;
    }

    @Override
    public String getInfo() {
        return "Clothing: " + getName()
                + " | ID: " + getId()
                + " | Price: $" + getPrice()
                + " | Size: " + size
                + " | Material: " + material;
    }}