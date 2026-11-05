package model;
public class Electronics extends Product implements Warranty {
    private String brand;
    private int warrantyYears;

    public Electronics(int id, String name, double price,
                       String category, String brand, int warrantyYears) {
        super(id, name, price, category);

        if (warrantyYears < 0) {
            throw new IllegalArgumentException("Warranty cannot be negative.");
        }

        this.brand = brand;
        this.warrantyYears = warrantyYears;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    @Override
    public String getWarrantyInfo() {
        return brand + " warranty: " + warrantyYears + " years";
    }

    @Override
    public String getInfo() {
        return "Electronics: " + getName()
                + " | ID: " + getId()
                + " | Price: $" + getPrice()
                + " | Brand: " + brand
                + " | Warranty: " + warrantyYears + " years";
    }}