package cn.acyou.aquarius.product.controller;

import cn.acyou.aquarius.product.common.Result;
import cn.acyou.aquarius.product.entity.Product;
import cn.acyou.aquarius.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2020/8/20]
 **/
@RestController
@RequestMapping("test")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping(value = "/list")
    public Result<?> list() {
        List<Product> products = productMapper.selectAll();
        return Result.success(products);
    }

    @GetMapping(value = "/outStock")
    @Transactional
    public List<String> outStock(){
        List<String> rs = new ArrayList<>();
        Product product = productMapper.selectByPrimaryKey(1);
        product.setStockNumber(product.getStockNumber() - 1);
        productMapper.updateByPrimaryKeySelective(product);
        System.out.println("扣减库存");
        rs.add("扣减库存成功！");
        return rs;
    }
}
