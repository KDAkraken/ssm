package chen.itheima.service;


import chen.itheima.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {
    public List<Product> findAll() throws Exception;
    public void save(Product product) throws Exception;

}
