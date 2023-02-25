package product.example.apiproduct.db;

import com.example.demoapp.model.Product;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtils {
    @Autowired
    private ResourceLoader resourceLoader;

    public List<Product> readFile(String fileName) {
        List<Product> productList = new ArrayList<>();

        Resource resource = resourceLoader.getResource("classpath:/static/" + fileName);

        try {
            FileReader filereader = new FileReader(resource.getFile());
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                Product product = new Product();
                product.setId(Integer.valueOf(row[0]));
                product.setName(row[1]);
                product.setPrice(Integer.valueOf(row[2]));
                product.setBrand(row[3]);
                product.setCount(Integer.valueOf(row[4]));

                productList.add(product);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
}

