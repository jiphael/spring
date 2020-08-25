package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.vo.OrderInfo;
import com.my.vo.Product;

@Service(value="productService")
public class ProductService {
	@Autowired
	
	private ProductDAO dao;
//	public ProductService() {
//		dao=new ProductDAO();
//	}

	public Product findByNo(String prod_no) throws FindException {
		Product p = dao.selectByNo(prod_no);
		return p;
	}
		
	public List<Product> findAll() throws FindException{
		return dao.selectAll();
	}

	public void add(OrderInfo info) {
		// TODO Auto-generated method stub
		
	}
		
	}

