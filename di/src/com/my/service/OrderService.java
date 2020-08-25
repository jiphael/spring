package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.vo.OrderInfo;

@Service(value="orderService")
public class OrderService {
	@Autowired
	@Qualifier(value = "orderDAO")
	private OrderDAO dao;
	public OrderService() {
		dao = new OrderDAO();
	}
	public void add(OrderInfo info) throws AddException {
		dao.insertInfo(info);
	}
	public List<OrderInfo> findById(String id) throws FindException{
		return dao.selectById(id);
	}
}
