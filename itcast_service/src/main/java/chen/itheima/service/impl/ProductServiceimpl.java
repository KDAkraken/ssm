package chen.itheima.service.impl;

import chen.itheima.dao.IProductDao;
import chen.itheima.domain.Product;
import chen.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceimpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    public ProductServiceimpl() {
        System.out.println("12313");
    }


    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {

        productDao.save(product);
    }


}
