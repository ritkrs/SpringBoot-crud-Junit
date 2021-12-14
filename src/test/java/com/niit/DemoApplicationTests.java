package com.niit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.niit.controller.ProductController;
import com.niit.dao.ProductRepo;
import com.niit.model.Product;
import com.niit.service.ProductService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DemoApplicationTests {
	@InjectMocks
	private ProductController productController; 
	@Mock
	private ProductService productService; 
	
	private MockMvc mockMvc;
	@Test
	public void getUserTest() {
		
		List<Product> product=new ArrayList<Product>();
		product.add(new Product(1,"mobile",10000));
		product.add(new Product(2,"television",2000));
		product.add(new Product(3,"watch",20000));
		//when(productService.listAll().)
		
		when(productService.listAll()).thenReturn(product);
		List<Product> users=productController.getList();
		assertEquals(3, users.size());
		//assertEquals(1, users.size());
		verify(productService,times(1)).listAll();
		
	}
	@Test
	public void saveTest() {
		//when(productService.)
		Product product=new Product(1,"earpod",9000990);
		productService.save(product);
		verify(productService,times(1)).save(product);
		
	}
	@Test
	public void getById() {
		when(productService.getById(1)).thenReturn(new Product(1,"earphone",87777));
		Product product=productService.getById(1);
		assertEquals(1, product.getId());
		assertEquals("earphone", product.getName());
		assertEquals(87777, product.getPrice());
		
	}	
	
//	@Test
//	public void whenGivenId_shouldDeleteUser_ifFound() throws Exception{
//	Product product = new Product();
//	product.setName("camera");
//	product.setId(3);
//	product.setPrice(9000);
//	when(productService.getById(2)).thenReturn(new Product(2,"mobile",9000));
//	mockMvc.perform(MockMvcRequestBuilders.delete("/applications",2)).andExpect(status().isOk());
//	
	
//	//when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
//	deleteUserService.deleteUser(user.getId());
//	verify(userRepository).deleteById(user.getId());
	//}
	
}
