package com.ensao.java.advanced.exercices.product;

@FunctionalInterface
public interface ProductPrinter extends Printer<Product> {
	void print(Product product);
}
