package com.company;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"cp1251");

        System.out.println("Введите количество товаров => ");
        int count = scanner.nextInt();
        scanner.nextLine();

        Product[] products = new Product[count];
        for (int i = 0; i < count; i++)
            products[i] = new Product();

        System.out.println("Введите информацию о каждом товаре: ");
        Integer maxCount = Integer.MIN_VALUE;
        ArrayList<Product> maxCountProducts = new ArrayList<Product>();
        Float averagePrice = 0.0f;
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

            // Товары с минимальным количеством
            if (maxCountProducts.isEmpty() || maxCount < currentProduct.GetCount()) {
                maxCount = currentProduct.GetCount();
                maxCountProducts = new ArrayList<Product>();
            }
            if (maxCount == currentProduct.GetCount()) {
                maxCountProducts.add(currentProduct);
            }

            // считаем среднюю цену
            averagePrice += currentProduct.GetPrice() / (float)count;
        }

        // вывод товаров с максимальным количеством единиц
        System.out.println("\nТовары с максимальным количеством единиц:");
        for (Product currentProduct : maxCountProducts) {
            System.out.println("\t" + currentProduct);
        }

        // подсчёт количества товаров ниже средней
        Integer cheapProductsCount = 0;
        for (Product currentProduct : products) {
            cheapProductsCount += (currentProduct.GetPrice() < averagePrice) ? 1 : 0;
        }
        // вывод средней цены и количества товаров с ценой ниже средней
        System.out.println("\nСредняя цена " + averagePrice + "\nКоличество товаров с ценой ниже среднего " + cheapProductsCount);

        // cортировка по убыванию цены
        Arrays.sort(products, new SortProductByPriceDescending());
        // вывод отсортированного массива
        System.out.println("\nТовары (отсортированы по убыванию):");
        for (Product currentProduct : products) {
            System.out.println("\t" + currentProduct);
        }

        while (true) {
            System.out.print("\nПоиск товаров:\nВведите наименование товара (для выхода напишите \"/quit\") => ");
            String query = scanner.nextLine();
            // Условие выхода
            if (query.matches("/quit\s*"))
                break;
            // Создание списка найденных товаров
            ArrayList<Product> searchResult = new ArrayList<Product>();
            for (Product product : products) {
                if (query.matches(product.GetName() + "\s*"))
                    searchResult.add(product);
            }

            Product pickedSearchResult = null;

            // Если не найден
            if (searchResult.isEmpty())
                System.out.println("Указанный товар не найден");
            else {
                // Если найдено много
                if (searchResult.size() > 1) {
                    System.out.println("Найденные товары:");
                    for (int i = 0; i < searchResult.size(); i++) {
                        System.out.println("\t" + i + ". " + searchResult.get(i));
                    }
                    System.out.println("Введите новер нужного товара из списка найденных");
                    Integer num = scanner.nextInt();
                    scanner.nextLine();
                    if ((num < 0) || (num >= searchResult.size())) { // Введено неправильное число
                        System.out.println("Такого товара не существует");
                    }
                    else {
                        pickedSearchResult = searchResult.get(num);
                    }
                }
                // Если найден только один
                else {
                    pickedSearchResult = searchResult.get(0);
                }
            }

            // Манипуляция с найденным товаром
            if (pickedSearchResult != null) {
                System.out.print("Есть такие поля:\n\t0. Наименование товара: " + pickedSearchResult.GetName() +
                        "\n\t1. Название производителя: " + pickedSearchResult.GetManufacturer() +
                        "\n\t2. Количество товаров: " + pickedSearchResult.GetCount() +
                        "\n\t3. Цена товара: " + pickedSearchResult.GetPrice() +
                        "\nВведите номер поля для изменения => ");
                Integer fieldNum = scanner.nextInt();
                scanner.nextLine();
                switch (fieldNum) {
                    case 0:
                        System.out.print("Введите новое наименование товара => ");
                        pickedSearchResult.SetName(scanner.nextLine());
                        break;
                    case 1:
                        System.out.print("Введите новое название производителя => ");
                        pickedSearchResult.SetManufacturer(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Введите новое количество товаров => ");
                        pickedSearchResult.SetCount(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("Введите новую цену товара => ");
                        pickedSearchResult.SetPrice(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("Введён неправильный номер поля");
                        continue;
                }
                System.out.println("Вот изменённый товар: " + pickedSearchResult);
            }
        }
    }
}
