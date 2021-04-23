package com.company;

public class Product {
    private String name;
    private String manufacturer;
    private Integer count;
    private Integer price;

    public Product(String name, String manufacturer, Integer count, Integer price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.count = count;
        this.price = price;
    }
    public Product() {
        this("", "", 0, 0);
    }

    public String GetName() {
        return name;
    }
    public String GetManufacturer() {
        return manufacturer;
    }
    public Integer GetCount() {
        return count;
    }
    public Integer GetPrice() {
        return price;
    }

    public void SetName(String newName) {
        this.name = newName;
    }
    public void SetManufacturer(String newManufacturer) {
        this.manufacturer = newManufacturer;
    }
    public void SetCount(Integer newCount) {
        this.count = newCount;
    }
    public void SetPrice(Integer newPrice) {
        this.price = newPrice;
    }

    public String toString() {
        return name + " " + manufacturer + " " + count + " " + price;
    }
}
