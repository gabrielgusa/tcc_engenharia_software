package br.com.findfoodtosave;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;

import br.com.findfoodtosave.productmanagement.application.rest.ProductController;

@SpringBootTest(useMainMethod = UseMainMethod.ALWAYS)
class ApiProductsApplicationTests {

	@Autowired
	private ProductController productController;

	@Test
	void contextLoads() {
		assertNotNull(this.productController);
	}

}
