package com.ensao.java.advanced.exercices.product;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		Product computer = new Product();
		computer.setId(1);
		computer.setName("Computers");
		computer.setPrice(400.00);
		computer.setCategory("IT");
		Product tv= new Product();
		tv.setId(2);
		tv.setPrice(300.00);
		tv.setName("TV");
		tv.setCategory("IT");
		Stock stock = new Stock();
		stock.add(computer);
		stock.add(tv);
		Map<String, List<Product>> groupedByCategory = stock.groupByCategory();
		groupedByCategory.forEach((cat,products)->{
			System.out.println(cat);
			products.stream().map(Product::getName).forEach(System.out::println);
		});

		Collection<String> productsNamesOnly = stock.map(product -> product.getName());
		Object foundProduct = stock.findProduct("Computer");
		Discount discount = new Discount() {
			@Override
			public Product discount(Product product, Double discount) {
				product.setPrice(product.getPrice()*(1-discount));
				return product;
			}
		}; // to complete
		stock.discount(discount,0.5);
		Stock expensiveThanComputer = stock.moreExpensiveThan(computer);
		Predicate<Product> predicate =p->p.getPrice()>300.00;
		Stock filtered = stock.filter(predicate); // to complete
		ProductPrinter printer=new ProductPrinter() {
			@Override
			public void print(Product product) {
				System.out.println("Name: "+product.getName()+"\nprice: "+product.getPrice()
						+"\nCategory: "+product.getCategory());
			}
		};
		filtered.print(printer);
		stock.findProduct("TV");

		
	}
}
