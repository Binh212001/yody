package org.example.yodybe.form;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.yodybe.entity.Product;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Getter
@Setter
public class ProductForm {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<Long> categories;
    private List<Long> colors;
    private List<Long> sizes;
    private List<MultipartFile> images;

    public Product mapEntity(){
        Product entity = new Product();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setPrice(this.price);
        return entity;
    }
}
