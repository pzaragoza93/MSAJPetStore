package interfaces;

import java.lang.Object;
import java.lang.String;
import java.util.List;
import java.util.Map;
import org.mybatis.jpetstore.domain.Item;
import org.springframework.cloud.openfeign.FeignClient;

import feign.RequestLine;
import feign.Headers;
import feign.Param;

//@FeignClient("itemsMapper")
public interface ProxyItemMapper {

    @RequestLine("GET /ItemMapper/getInventoryQuantity?itemId={itemId}")
    int getInventoryQuantity(@Param("itemId") String itemId);

    @RequestLine("POST /ItemMapper/updateInventoryQuantity")
    @Headers("Content-Type: application/json")
    void updateInventoryQuantity(Map<String, Object> param);

    @RequestLine("GET /ItemMapper/getItemListByProduct?productId={productId}")
    List<Item> getItemListByProduct(@Param("productId") String productId);

    @RequestLine("GET /ItemMapper/getItem?itemId={itemId}")
    Item getItem(@Param("itemId") String itemId);

}