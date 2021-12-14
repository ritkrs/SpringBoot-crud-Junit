package com.niit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.niit.model.Product;
@DataJpaTest
public class ProductTest {
	@Test
	public void testCreate() {
		Product p=new Product();
		p.setId(1);
		p.setName("mobile");
		p.setPrice(100);
		
	}

}
