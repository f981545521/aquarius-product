package cn.acyou.aquarius.product.controller;

import cn.acyou.aquarius.product.common.Result;
import cn.acyou.aquarius.product.entity.Product;
import cn.acyou.aquarius.product.mapper.ProductMapper;
import cn.acyou.aquarius.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2020/8/20]
 **/
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping(value = "/list")
    public Result<?> list() {
        List<Product> products = productMapper.selectAll();
        return Result.success(products);
    }
}
