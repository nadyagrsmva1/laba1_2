package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainWithMethods {
    private static Scanner scanner = null;
    private static Product[] products = null;
    private static int count = 0;

    private static ArrayList<Product> GetMaxCountProducts() {
        Integer maxCount = Integer.MIN_VALUE;
        ArrayList<Product> maxCountProducts = new ArrayList<Product>();
        for (Product currentProduct : products) {
            // Товары с максимальным количеством
            if (maxCountProducts.isEmpty() || maxCount < currentProduct.GetCount()) {
                maxCount = currentProduct.GetCount();
                maxCountProducts = new ArrayList<Product>();
            }
            if (maxCount == currentProduct.GetCount()) {
                maxCountProducts.add(currentProduct);
            }
        }
        return maxCountProducts;
    }

    private static Integer GetCheaperThanAverageProductsCount() {
        // считаем среднюю цену
        Float averagePrice = 0.0f;
        for (Product currentProduct : products) {
            averagePrice += currentProduct.GetPrice() / (float) count;
        }
        // подсчёт количества товаров ниже средней
        Integer cheapProductsCount = 0;
        for (Product currentProduct : products) {
            cheapProductsCount += (currentProduct.GetPrice() < averagePrice) ? 1 : 0;
        }
        return cheapProductsCount;
    }

    private static void Sort() {
        Arrays.sort(products, new SortProductByPriceDescending());
    }

    private static ArrayList<Product> Search(String query) {
        // Создание списка найденных товаров
        ArrayList<Product> searchResult = new ArrayList<Product>();
        for (Product product : products) {
            if (query.matches(product.GetName() + "\s*"))
                searchResult.add(product);
        }
        return searchResult;
    }

    private static Product PickFromMany(ArrayList<Product> productsArray) {
        Product pickedSearchResult = null;

        // Если не найден
        if (productsArray.isEmpty())
            System.out.println("Указанный товар не найден");
        else {
            // Если найдено много
            if (productsArray.size() > 1) {
                System.out.println("Найденные товары:");
                for (int i = 0; i < productsArray.size(); i++) {
                    System.out.println("\t" + i + ". " + productsArray.get(i));
                }
                System.out.println("Введите новер нужного товара из списка найденных");
                Integer num = scanner.nextInt();
                scanner.nextLine();
                if ((num < 0) || (num >= productsArray.size())) { // Введено неправильное число
                    System.out.println("Такого товара не существует");
                }
                else {
                    pickedSearchResult = productsArray.get(num);
                }
            }
            // Если найден только один
            else {
                pickedSearchResult = productsArray.get(0);
            }
        }
        return pickedSearchResult;
    }

    private static void Edit(Product product) {
        // Манипуляция с найденным товаром
        if (product != null) {
            System.out.print("Есть такие поля:\n\t0. Наименование товара: " + product.GetName() +
                    "\n\t1. Название производителя: " + product.GetManufacturer() +
                    "\n\t2. Количество товаров: " + product.GetCount() +
                    "\n\t3. Цена товара: " + product.GetPrice() +
                    "\nВведите номер поля для изменения => ");
            Integer fieldNum = scanner.nextInt();
            scanner.nextLine();
            switch (fieldNum) {
                case 0:
                    System.out.print("Введите новое наименование товара => ");
                    product.SetName(scanner.nextLine());
                    break;
                case 1:
                    System.out.print("Введите новое название производителя => ");
                    product.SetManufacturer(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Введите новое количество товаров => ");
                    product.SetCount(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Введите новую цену товара => ");
                    product.SetPrice(scanner.nextInt());
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Введён неправильный номер поля");
                    return;
            }
            System.out.println("Вот изменённый товар: " + product);
        }
    }

    private static void EditProccess() {
        while (true) {
            System.out.print("\nПоиск товаров:\nВведите наименование товара (для выхода напишите \"/quit\") => ");
            String query = scanner.nextLine();
            // Условие выхода
            if (query.matches("/quit\s*"))
                break;
            ArrayList<Product> searchResult = Search(query);

            Product pickedSearchResult = PickFromMany(searchResult);

            Edit(pickedSearchResult);
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in,"cp1251");

        System.out.println("Введите количество товаров => ");
        count = scanner.nextInt();
        scanner.nextLine();

        products = new Product[count];
        for (int i = 0; i < count; i++)
            products[i] = new Product();

        System.out.println("Введите информацию о каждом товаре: ");
        for (Product currentProduct : products) {
            System.out.print("Введите наименование товара => ");
            currentProduct.SetName(scanner.nextLine());
            System.out.print("Введите название производителя => ");
            currentProduct.SetManufacturer(scanner.nextLine());
            System.out.print("Введите количество товаров => ");
            currentProduct.SetCount(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Введите цену одного товара => ");
            currentProduct.SetPrice(scanner.nextInt());
            scanner.nextLine();
            System.out.print("\n");
        }

        // вывод товаров с максимальным количеством единиц
        ArrayList<Product> maxCountProducts = GetMaxCountProducts();
        System.out.println("\nТовары с максимальным количеством единиц:");
        for (Product currentProduct : maxCountProducts) {
            System.out.println("\t" + currentProduct);
        }

        // вывод средней цены и количества товаров с ценой ниже средней
        Integer cheapProductsCount = GetCheaperThanAverageProductsCount();
        System.out.println("\nКоличество товаров с ценой ниже среднего " + cheapProductsCount);

        // cортировка по убыванию цены
        Sort();
        // вывод отсортированного массива
        System.out.println("\nТовары (отсортированы по убыванию):");
        for (Product currentProduct : products) {
            System.out.println("\t" + currentProduct);
        }

        EditProccess();
    }
}
