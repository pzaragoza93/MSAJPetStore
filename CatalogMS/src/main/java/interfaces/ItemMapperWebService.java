package interfaces;

import java.lang.Object;
import java.lang.String;
import java.util.List;
import java.util.Map;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.mapper.ItemMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ItemMapperWebService {

    @Autowired
    public ItemMapper itemMapper;

    @GetMapping("/ItemMapper/getInventoryQuantity")
    int getInventoryQuantity(@RequestParam("itemId") String itemId){
        return itemMapper.getInventoryQuantity(itemId);
    }

    @PostMapping("/ItemMapper/updateInventoryQuantity")
    void updateInventoryQuantity(@RequestBody Map<String, Object> param){
        itemMapper.updateInventoryQuantity(param);
    }

    @GetMapping("/ItemMapper/getItemListByProduct")
    List<Item> getItemListByProduct(@RequestParam("productId") String productId){
        return itemMapper.getItemListByProduct(productId);
    }

    @GetMapping("/ItemMapper/getItem")
    Item getItem(@RequestParam("itemId") String itemId){
        return itemMapper.getItem(itemId);
    }

}