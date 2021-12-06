package com.reactive.springboot.utils;

import org.springframework.beans.BeanUtils;
import com.reactive.springboot.entity.Product;
import com.reactive.springboot.model.ProductDtls;

public class AppUtils {

	public static ProductDtls entityToModel(Product product) {
		ProductDtls productDtls = new ProductDtls();
		BeanUtils.copyProperties(product, productDtls);
		return productDtls;
	}

	public static Product modelToEntity(ProductDtls productDtls) {
		Product product = new Product();
		BeanUtils.copyProperties(productDtls, product);
		return product;
	}
}
