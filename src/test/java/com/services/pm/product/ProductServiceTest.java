package com.services.pm.product;

import com.services.pm.product.model.Product;
import com.services.pm.product.model.enumeration.RatingValues;
import com.services.pm.product.repository.ProductRepository;
import com.services.pm.product.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore
    public void testFindAll() {
        //MatcherAssert.assertThat(productService.findAll(), Matchers.hasSize(2));
        assertThat(productService.findAll(), hasSize(2));
    }

    @Test
    public void testFindById() {
        Product product = new Product.ProductBuilder(1)
                .withName("Iphone 5")
                .withPrice(500)
                .withRating(RatingValues.FOUR.getValue())
                .build();

        //Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));

        Mockito.when(productRepository.getOne(1L)).thenReturn(product);

        Product resultProduct = productService.findById(1L);

        assertThat(resultProduct, Matchers.equalTo(product));

        verify(productRepository).getOne(1L);
    }
}
