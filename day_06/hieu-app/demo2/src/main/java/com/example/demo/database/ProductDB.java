package com.example.demo.database;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;

import com.example.demo.model.Product;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;

public class ProductDB {
    //    public static List<Product> products = new ArrayList<>(List.of(
//            new Product(1, "Iphone 13", "Apple", 30_000_000, 4),
//            new Product(2, "Nokia 11", "Nokia", 5_000_000, 8),
//            new Product(3, "Iphone 14", "Apple", 16_000_000, 6)
//    ));
    public static String[] header;
    public static List<Product> products = getProductByFile();

    public static List<Product> getProductByFile() {
        String csvFile = "file.csv";
        List<Product> products = new ArrayList<>();
        try {
            // Lấy đối tượng InputStream của tập tin CSV từ thư mục resources
            InputStream inputStream = new ClassPathResource(csvFile).getInputStream();

            // Khởi tạo đối tượng CSVReader để đọc dữ liệu từ InputStream
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

            // Đọc header từ tập tin
            header = reader.readNext();

            // Đọc dữ liệu từ tập tin và tạo đối tượng Product
            String[] line;
            while ((line = reader.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                String brand = line[2];
                Double price = Double.parseDouble(line[3]);
                int count = Integer.parseInt(line[4]);

                // Tạo đối tượng Product từ dữ liệu đọc được từ file CSV
                Product product = new Product(id, name, brand, price, count);

                // Thực hiện các thao tác với đối tượng product tại đây
                products.add(product);
            }
//            for (Product product : products) {
//                System.out.println("Name " + product.getName() + "price " + product.getPrice() + "count " + product.getCount());
//            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void saveProductToFile() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/product2.csv");

            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeNext(header);
            products.stream()
                    .map(product -> new String[]{product.getId() + "," + product.getName() + "," + product.getBrand() + "," + product.getPrice() + "," + product.getCount()})
                    .forEach(csvWriter::writeNext);

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}