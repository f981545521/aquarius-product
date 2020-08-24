package cn.acyou.aquarius.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author youfang
 * @date 2018-04-15 下午 07:36
 **/
@Data
@EqualsAndHashCode
@Table(name = "t_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 5350645545628778721L;

    @Id
    @Column(name = "product_id")
    @GeneratedValue(generator = "JDBC")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock_number")
    private Integer stockNumber;

    @Column(name = "status")
    private Integer status;


}
