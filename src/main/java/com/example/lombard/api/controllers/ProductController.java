package com.example.lombard.api.controllers;


import com.example.lombard.api.facade.ProductFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {
  private final ProductFacade productFacade;
  @GetMapping
  public String getProductsPage(Model model){
    productFacade.getProductsPage(model);
    return "products";
  }
  @GetMapping("/buy/{productId}")
  public String sellProduct(@PathVariable("productId") Long productId){
    this.productFacade.sellProduct(productId);
    return "redirect:/products";
  }
}
