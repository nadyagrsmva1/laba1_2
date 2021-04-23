package com.company;
import java.util.Comparator;

public class SortProductByPriceDescending implements Comparator<Product> {
    public int compare(Product left, Product right)
    {
        return right.GetCount() - left.GetCount();
    }
}
