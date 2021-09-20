/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package interfaces;

import java.lang.String;
import java.util.List;
import java.util.List;
import java.util.List;
import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import feign.RequestLine;
import feign.Param;

public interface ProxyCatalogService {

  @RequestLine("GET /CatalogService/getItem?itemId={itemId}")
  public Item getItem(@Param("itemId") String itemId);

  @RequestLine("GET /CatalogService/getProductListByCategory?categoryId={categoryId}")
  public List<Product> getProductListByCategory(@Param("categoryId") String categoryId);

  @RequestLine("GET /CatalogService/getItemListByProduct?productId={productId}")
  public List<Item> getItemListByProduct(@Param("productId") String productId);

  @RequestLine("GET /CatalogService/getProduct?productId={productId}")
  public Product getProduct(@Param("productId") String productId);

  @RequestLine("GET /CatalogService/getCategoryList")
  public List<Category> getCategoryList();

  @RequestLine("GET /CatalogService/isItemInStock?itemId={itemId}")
  public boolean isItemInStock(@Param("itemId") String itemId);

  @RequestLine("GET /CatalogService/getCategory?categoryId={categoryId}")
  public Category getCategory(@Param("categoryId") String categoryId);

  @RequestLine("GET /CatalogService/searchProductList?keywords={keywords}")
  public List<Product> searchProductList(@Param("keywords") String keywords);

}