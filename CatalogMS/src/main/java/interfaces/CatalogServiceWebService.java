package interfaces;

import java.lang.String;
import java.util.List;
import java.util.List;
import java.util.List;
import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.mapper.CategoryMapper;
import org.mybatis.jpetstore.mapper.ItemMapper;
import org.mybatis.jpetstore.mapper.ProductMapper;
import org.mybatis.jpetstore.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class CatalogServiceWebService {

    @Autowired
    public CatalogService catalogService;

    @GetMapping("/CatalogService/getItem")
    public Item getItem(@RequestParam("itemId") String itemId){
        return catalogService.getItem(itemId);
    }

    @GetMapping("/CatalogService/getProductListByCategory")
    public List<Product> getProductListByCategory(@RequestParam("categoryId") String categoryId){
        return catalogService.getProductListByCategory(categoryId);
    }

    @GetMapping("/CatalogService/getItemListByProduct")
    public List<Item> getItemListByProduct(@RequestParam("productId") String productId){
        return catalogService.getItemListByProduct(productId);
    }

    @GetMapping("/CatalogService/getProduct")
    public Product getProduct(@RequestParam("productId") String productId){
        return catalogService.getProduct(productId);
    }

    @GetMapping("/CatalogService/getCategoryList")
    public List<Category> getCategoryList(){
        return catalogService.getCategoryList();
    }

    @GetMapping("/CatalogService/isItemInStock")
    public boolean isItemInStock(@RequestParam("itemId") String itemId){
        return catalogService.isItemInStock(itemId);
    }

    @GetMapping("/CatalogService/getCategory")
    public Category getCategory(@RequestParam("categoryId") String categoryId){
        return catalogService.getCategory(categoryId);
    }

    @GetMapping("/CatalogService/searchProductList")
    public List<Product> searchProductList(@RequestParam("keywords") String keywords){
        return catalogService.searchProductList(keywords);
    }

}