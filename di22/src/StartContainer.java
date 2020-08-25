import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.exception.FindException;
import com.my.service.CustomerService2;
import com.my.service.ProductService;
import com.my.vo.MapVO;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;
import com.my.vo.PropertiesVO;

public class StartContainer {

	public static void main(String[] args) throws FileNotFoundException, IOException, FindException {
		//스프링 컨테이너 구동
		ClassPathXmlApplicationContext ctx;
		String configLocation = "configuration.xml";
		ctx = new ClassPathXmlApplicationContext(configLocation);
		
//		AnnotationConfigApplicationContext ctx = 
//				new AnnotationConfigApplicationContext(com.my.config.Configuration.class);
		Product p3 = ctx.getBean("p", Product.class);
		System.out.println(p3);
		
		
		Product p1 = ctx.getBean("p", com.my.vo.Product.class); //��ü�� �ϳ����������. ������ �⺻������ �̱�������.
		Product p2 = ctx.getBean("p", com.my.vo.Product.class);
		System.out.println(p1==p2);
		System.out.println(p1.getProd_no());
		System.out.println(p1.getProd_name());
		System.out.println(p1.getProd_price());
		
		OrderLine line = ctx.getBean("line", com.my.vo.OrderLine.class);
		System.out.println(line);
		
		OrderInfo info =ctx.getBean("info", OrderInfo.class);
		System.out.println(info);
		
		MapVO mapvo = ctx.getBean("mapvo", MapVO.class);
		Map map = mapvo.getMap();
		System.out.println(map);
		
		PropertiesVO propertiesvo =ctx.getBean("propertiesvo", PropertiesVO.class);
		//System.out.println(propertiesvo.getEnv());
		
		Properties env = propertiesvo.getEnv();
		String fileName = env.getProperty("msg");
		Properties env1 = new Properties();
		env1.load(new FileInputStream(fileName));
		String greeting = env1.getProperty("greeting");
		System.out.println(greeting);
		
//		ProductDAO productDAO = ctx.getBean("productDAO",com.my.dao.ProductDAO.class);
//		productDAO.selectAll();
		
		ProductService productService = ctx.getBean("productService", ProductService.class);
		productService.findAll();
		
		List<Product> list = productService.findAll();	
		System.out.println(list);
		
		CustomerService2 customerService = ctx.getBean("customerService",CustomerService2.class);
		customerService.login("id1", "p1");
		System.out.println("로그인성공");
		System.out.println("-------------------------");
		

	}
	
}
