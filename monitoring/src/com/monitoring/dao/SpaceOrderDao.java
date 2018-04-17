package com.monitoring.dao;


import com.monitoring.entity.SpaceOrder;

import java.util.List;

/**
 * @author mry
 *2018年1月2日 上午11:17:03
 */
public interface SpaceOrderDao {
	public SpaceOrder getSpaceOrderById(int id);

	public SpaceOrder getSpaceOrderByUserId(int userId);

	public SpaceOrder getSpaceOrderByName(String name);

	public void addSpaceOrder(SpaceOrder spaceOrder);

	public int deleteSpaceOrderById(int id);

	public void saveOrupdate(SpaceOrder spaceOrder);

	public List<SpaceOrder> getSpaceOrders();

	public List<SpaceOrder> getSpaceOrdersPageList(int pageNum);

	public int pageS();

	public Integer isOrNadd(int id);

	public Integer updateSpaceOrder(SpaceOrder spaceOrder);
}
