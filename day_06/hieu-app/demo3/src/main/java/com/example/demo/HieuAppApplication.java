package com.example.demo;

import com.example.demo.model.Product;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SpringBootApplication
public class HieuAppApplication {

	public static void main(String[] args) throws IOException, URISyntaxException, CsvException {
		List<Product> products = readCsvToObject();
		//SpringApplication.run(HieuAppApplication.class, args);
	}
//	private static void readCsvWithEmbeddedSpecial() throws IOException, CsvException, URISyntaxException {
//
//		// loads file from resource folder
//		URL resource = HieuAppApplication.class.getClassLoader().getResource("file.csv");
//		File file = Paths.get(resource.toURI()).toFile();
//
//		List<String[]> r;
//		try (CSVReader reader = new CSVReader(new FileReader(file))) {
//			r = reader.readAll();
//		}
//
//		// print result
//		int listIndex = 0;
//		for (String[] arrays : r) {
//			System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays));
//
//			int index = 0;
//			for (String array : arrays) {
//				System.out.println(index++ + " : " + array);
//			}
//
//		}
//
//	}
//
	private static List<Product> readCsvToObject() throws IOException, CsvException {
		String csvFile = "file.csv";
		List<Product> products = new ArrayList<>();
		try {
			// Lấy đối tượng InputStream của tập tin CSV từ thư mục resources
			InputStream inputStream = new ClassPathResource(csvFile).getInputStream();

			// Khởi tạo đối tượng CSVReader để đọc dữ liệu từ InputStream
			CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

			// Đọc header từ tập tin
			String[] header = reader.readNext();

			// Đọc dữ liệu từ tập tin và tạo đối tượng Product
			String[] line;
			while ((line = reader.readNext()) != null) {
				int id = Integer.parseInt(line[0]);
				String name = line[1];
				String brand = line[2];
				int price = Integer.parseInt(line[3]);
				int count = Integer.parseInt(line[4]);

				// Tạo đối tượng Product từ dữ liệu đọc được từ file CSV
				Product product = new Product(id, name, brand, price, count);

				// Thực hiện các thao tác với đối tượng product tại đây
				products.add(product);
			}
			for (Product product : products) {
				System.out.println("Name " + product.getName());
			}
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		return products;
	}
//
//	private static List<String[]> readCsvFileCustomSeparator(String fileName, char separator) throws IOException, CsvException {
//
//		List<String[]> r;
//
//		// custom separator
//		CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).build();
//		try (CSVReader reader = new CSVReaderBuilder(
//				new FileReader(fileName))
//				.withCSVParser(csvParser)   // custom CSV parser
//				.withSkipLines(1)           // skip the first line, header info
//				.build()) {
//			r = reader.readAll();
//		}
//		return r;
//
//	}
//
//	private static List<String[]> readCsvFile(String fileName) throws IOException, CsvException {
//
//		List<String[]> r;
//		try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
//			r = reader.readAll();
//		}
//
//		// read line by line
//        /*try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
//            String[] lineInArray;
//            while ((lineInArray = reader.readNext()) != null) {
//                r.add(lineInArray);
//            }
//        }*/
//
//		return r;
//
//	}
}
