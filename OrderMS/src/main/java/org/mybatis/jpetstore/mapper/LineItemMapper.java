package org.mybatis.jpetstore.mapper;

import java.util.List;
import org.mybatis.jpetstore.domain.LineItem;

public interface LineItemMapper {


List<LineItem> getLineItemsByOrderId(int orderId);

void insertLineItem(LineItem lineItem);

}