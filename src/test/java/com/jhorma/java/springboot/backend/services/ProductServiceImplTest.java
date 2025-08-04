package com.jhorma.java.springboot.backend.services;

import com.jhorma.java.springboot.backend.entities.Product;
import com.jhorma.java.springboot.backend.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Laptop");
        sampleProduct.setPrice(10000L);
    }

    @Test
    void testFindAll() {
        List<Product> products = List.of(sampleProduct);
        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.findAll();

        assertThat(result).hasSize(1);
        verify(repository).findAll();
    }

    @Test
    void testFindByIdFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        Optional<Product> result = service.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Laptop");
        verify(repository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Optional<Product> result = service.findById(2L);

        assertThat(result).isEmpty();
        verify(repository).findById(2L);
    }

    @Test
    void testSave() {
        when(repository.save(sampleProduct)).thenReturn(sampleProduct);

        Product saved = service.save(sampleProduct);

        assertThat(saved).isNotNull();
        verify(repository).save(sampleProduct);
    }

    @Test
    void testDeleteByIdFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        Optional<Product> result = service.deleteById(1L);

        assertThat(result).isPresent();
        verify(repository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Optional<Product> result = service.deleteById(2L);

        assertThat(result).isEmpty();
        verify(repository, never()).deleteById(anyLong());
    }
}
