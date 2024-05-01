package com.example.lombard.api.facade;

import com.example.lombard.core.exception.LessMoneyException;
import com.example.lombard.core.service.PhotoService;
import com.example.lombard.core.service.ProductService;
import com.example.lombard.core.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.IOException;

@AllArgsConstructor
@Component
public class ProductFacade {
  private final ProductService productService;
  private final UserService userService;
  private final PhotoService photoService;
  public void getProductsPage(Model model){
    model.addAttribute("products", productService.getProducts());
  }
  @SneakyThrows
  public void sellProduct(Long productId){
    //TODO remake on getting from authentication
    Long userId = Long.valueOf(1);
    var user = userService.getUser(userId);
    var product = productService.findProductById(productId);
    if(user.getAccount()<product.getPrice()){
      //TODO make error handling
      throw new LessMoneyException("Недостаточно средств для осуществления операции");
    }
    user.setAccount(user.getAccount()-product.getPrice());
    userService.saveUser(user);
    photoService.deleteFileByName(product.getPhotoName());
    productService.sellProduct(productId);
  }
}
