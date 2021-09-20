package org.mybatis.jpetstore.service;

import java.lang.Integer;
import java.lang.Object;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Order;
import org.mybatis.jpetstore.domain.Sequence;
import org.mybatis.jpetstore.mapper.LineItemMapper;
import org.mybatis.jpetstore.mapper.OrderMapper;
import org.mybatis.jpetstore.mapper.SequenceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feign.Feign;
import interfaces.ProxyItemMapper;

@Service
public class OrderService {
	private final ProxyItemMapper itemMapper;
	private final OrderMapper orderMapper;
	private final SequenceMapper sequenceMapper;
	private final LineItemMapper lineItemMapper;

	//
	public OrderService(ProxyItemMapper itemMapper, OrderMapper orderMapper, SequenceMapper sequenceMapper, LineItemMapper lineItemMapper) {
		this.itemMapper = itemMapper;
		this.orderMapper = orderMapper;
		this.sequenceMapper = sequenceMapper;
		this.lineItemMapper = lineItemMapper;

	}

	@Transactional
	public void insertOrder(Order order) {
		
		order.setOrderId(getNextId("ordernum"));
		order.getLineItems().forEach(lineItem -> {
			String itemId = lineItem.getItemId();
			Integer increment = lineItem.getQuantity();
			Map<String, Object> param = new HashMap<>(2);
			param.put("itemId", itemId);
			param.put("increment", increment);
			//itemMapper.updateInventoryQuantity(param);
		});
		System.out.println("username if not null is: "+ order.getUsername());
		orderMapper.insertOrder(order);
		orderMapper.insertOrderStatus(order);
		order.getLineItems().forEach(lineItem -> {
			lineItem.setOrderId(order.getOrderId());
			lineItemMapper.insertLineItem(lineItem);
		});

	}

	@Transactional
	public Order getOrder(int orderId) {

		Order order = orderMapper.getOrder(orderId);
		order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));

		order.getLineItems().forEach(lineItem -> {
			System.out.println("fetching item for this line: " + lineItem.getOrderId());
			Item item = itemMapper.getItem(lineItem.getItemId());
			item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
			lineItem.setItem(item);
		});

		return order;

	}

	public List<Order> getOrdersByUsername(String username) {

		return orderMapper.getOrdersByUsername(username);

	}

	public int getNextId(String name) {

		Sequence sequence = sequenceMapper.getSequence(new Sequence(name, -1));
		if (sequence == null) {
			throw new RuntimeException("Error: A null sequence was returned from the database (could not get next "
					+ name + " sequence).");
		}
		Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
		sequenceMapper.updateSequence(parameterObject);
		return sequence.getNextId();

	}

}