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
package org.mybatis.jpetstore.domain;

import java.io.Serializable;
import java.lang.Object;
import java.lang.String;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.mybatis.jpetstore.domain.CartItem;
import org.mybatis.jpetstore.domain.Item;

public class Cart implements Serializable {
  private static final long serialVersionUID = 8329559983943337176L;
  private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<>());
  private final List<CartItem> itemList = new ArrayList<>();

  static {

  }

  public Iterator<CartItem> getCartItems() {

    return itemList.iterator();

  }

  public List<CartItem> getCartItemList() {

    return itemList;

  }

  public int getNumberOfItems() {

    return itemList.size();

  }

  public Iterator<CartItem> getAllCartItems() {

    return itemList.iterator();

  }

  public boolean containsItemId(String itemId) {

    return itemMap.containsKey(itemId);

  }

  public void addItem(Item item, boolean isInStock) {

    CartItem cartItem = itemMap.get(item.getItemId());
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setItem(item);
      cartItem.setQuantity(0);
      cartItem.setInStock(isInStock);
      itemMap.put(item.getItemId(), cartItem);
      itemList.add(cartItem);
    }
    cartItem.incrementQuantity();

  }

  public Item removeItemById(String itemId) {

    CartItem cartItem = itemMap.remove(itemId);
    if (cartItem == null) {
      return null;
    } else {
      itemList.remove(cartItem);
      return cartItem.getItem();
    }

  }

  public void incrementQuantityByItemId(String itemId) {

    CartItem cartItem = itemMap.get(itemId);
    cartItem.incrementQuantity();

  }

  public void setQuantityByItemId(String itemId, int quantity) {

    CartItem cartItem = itemMap.get(itemId);
    cartItem.setQuantity(quantity);

  }

  public BigDecimal getSubTotal() {

    return itemList.stream()
        .map(cartItem -> cartItem.getItem().getListPrice().multiply(new BigDecimal(cartItem.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

  }

}