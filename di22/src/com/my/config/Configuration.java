package com.my.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.my.vo.Customer;
import com.my.vo.MapVO;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;
import com.my.vo.PropertiesVO;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean(name="p")
	public com.my.vo.Product getP(){
//		Product p = new Product();
//		p.setProd_no("C0001");
//		return p;
		return new Product("C0001", "테스트", 1000);
	}
	

	@Bean(name="line2")
	public OrderLine getOrderLine1() {		
		OrderLine line = new OrderLine();
		line.setOrder_no(1);
		line.setOrder_p(getP());
		line.setOrder_quantity(2);
		return line;
	}
	
	@Bean(name="c")
	public Customer getCustomer() {
		Customer c = new Customer();
		c.setId("id1");
		c.setPwd("p1");
		c.setName("홍길동");
		c.setAddr("");
		return c;
	}

	@Bean(name = "line")
	public OrderLine getOrderLine() {
		OrderLine line  = new OrderLine();
		line.setOrder_no(1);
		line.setOrder_p(getP());
		line.setOrder_quantity(5);
		return line;
	}
	@Bean(name="line2")
	public OrderLine getOrderLine2() {
		OrderLine line = new OrderLine();
		line.setOrder_no(1);
		line.setOrder_p(getP());
		line.setOrder_quantity(2);
		return line;
	}
	@Bean(name="info")
	public OrderInfo getOrderInfo() {
		OrderInfo info = new OrderInfo(getCustomer());
		info.setOrder_dt(null);
		List<OrderLine>lines = new ArrayList<>();
		lines.add(getOrderLine());
		lines.add(getOrderLine2());
		info.setLines(lines);
		return info;
	}
	
	@Bean(name="mapvo")
	public MapVO getMapVO() {
		MapVO mapvo = new MapVO();
		Map<String, Integer> map = new HashMap<>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		mapvo.setMap(map);
		return mapvo;
	}
	@Bean(name="propertiesvo")
	public PropertiesVO getPropertiesVO() {
		Properties env = new Properties();
		env.put("controller", "controller.properties");
		env.put("dao", "dao.properties");
		env.put("msg", "msg.properties");		
		return new PropertiesVO(env);
	}
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		SimpleDriverDataSource sds =
                                   new SimpleDriverDataSource();
		sds.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
		sds.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:XE");
		sds.setUsername("test");
		sds.setPassword("test");
		return sds;
	}

	
	
}
