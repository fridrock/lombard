package com.example.lombard.core.service;


import com.example.lombard.core.exception.NotFoundException;
import com.example.lombard.core.model.Product;
import com.example.lombard.core.repositories.ObligationRepository;
import com.example.lombard.core.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@AllArgsConstructor
@Service
public class ProductService {
  private final ObligationRepository obligationRepository;
  private final ProductRepository productRepository;
  public void updateProducts(){
    var calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-6);
    var yearBefore = new Date(calendar.getTimeInMillis());
    var expiredObligations = obligationRepository.findBeforeDate(yearBefore);
    expiredObligations.forEach(expiredObligation->{
      var product = new Product();
      product.setDescription(expiredObligation.getDescription());
      product.setPrice(expiredObligation.getDebt()/1.5);
      product.setPhotoName(expiredObligation.getPhotoName());
      productRepository.save(product);
      obligationRepository.deleteById(expiredObligation.getObligationId());
    });
  }
  public Product findProductById(Long productId){
    //TODO make error handling
    return productRepository.findById(productId).orElseThrow(()->new NotFoundException("Нет такого продукта"));
  }
  public List<Product> getProducts(){
    return productRepository.findAll();
  }
  public void sellProduct(Long productId){
    productRepository.deleteById(productId);
  }

}
