package chen.itheima.controller;

import chen.itheima.domain.Product;
import chen.itheima.service.impl.ProductServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
//    @Qualifier("productService")
    private ProductServiceimpl productServiceimpl;

    @RequestMapping("/findAll.do")
    @Secured("admin")
    public ModelAndView findAll() throws Exception {
        List<Product> productList = productServiceimpl.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("productList", productList);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productServiceimpl.save(product);
        return "redirect:findAll.do";
    }

}
