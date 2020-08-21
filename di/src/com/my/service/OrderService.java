package com.my.service;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.vo.OrderInfo;

public class OrderService {
	private OrderDAO dao;
	
	public OrderService() {
		dao = new OrderDAO();
	}
	
	public void add(OrderInfo info) throws AddException{
		dao.insert(info);
	}


}
