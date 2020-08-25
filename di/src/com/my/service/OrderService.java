package com.my.service;

import java.util.List;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.OrderInfo;

public class OrderService {
	private OrderDAO dao;
	public OrderService() {
		dao = new OrderDAO();
	}
	public void add(OrderInfo info) throws AddException {
		dao.insert(info);
	}
	public List<OrderInfo> findById(String id) throws FindException{
		return dao.selectById(id);
	}
}
