package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
   @Autowired
   ProductService productService;

    @GetMapping("/products")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView("showProduct");
        modelAndView.addObject("products",productService.products);
        return modelAndView;
    }



    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        productService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.products.get(productService.findIndexByID(id));
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Product product, @PathVariable int id, @RequestParam MultipartFile uppImg) throws IOException{
        String fileName = uppImg.getOriginalFilename();
        FileCopyUtils.copy(uppImg.getBytes(), new File("C:\\Users\\Admin\\IdeaProjects\\demo7\\src\\main\\webapp\\WEB-INF\\img\\" + fileName));
        product.setImg("/"+fileName);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.edit(id,product );
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showcreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product, @RequestParam MultipartFile uppImg) throws IOException {
        String fileName = uppImg.getOriginalFilename();
        FileCopyUtils.copy(uppImg.getBytes(), new File("C:\\Users\\Admin\\IdeaProjects\\demo7\\src\\main\\webapp\\WEB-INF\\img\\" + fileName));
        product.setImg("/"+fileName);
        productService.add(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }


}