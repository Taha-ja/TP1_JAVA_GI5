package com.ensao.java.advanced.exercices.product;

@FunctionalInterface
public interface Discount {
	Product discount(Product product, Double discount);
}
