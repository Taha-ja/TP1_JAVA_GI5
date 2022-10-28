package com.ensao.java.advanced.exercices.product;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Stock extends TreeSet<Product> {
	private static final ProductComparator COMPARATOR = new ProductComparator();
	public Stock(){
		super(COMPARATOR);
	}
	public Stock filter(Predicate<Product> predicate) {
		/*throw new ToBeCompletedException("Return a Stock instance containing products " +
				"to which is applied the predicate");*/
		return this.stream().filter(predicate).collect(Collectors.toCollection(Stock::new));
	}
	
	public void discount(Discount discount,double amount) {
		
		/*throw new ToBeCompletedException("apply a discount function " +
				" do not apply discount if discount amount is > 1 or < 0");*/
		Consumer<Product> consumer = product -> {
			discount.discount(product,amount);
		};
		this.stream().forEach(consumer);
	}
	
	public <R> Collection<R> map(Function<Product, R> mapper) {
		/*throw new ToBeCompletedException("Retrun a collection of mapped property " +
				"of type 'R' of a product");*/
		Collection <R> collection=this.stream().map(mapper).collect(Collectors.toList());
		return collection;
	}
	
	public void print(ProductPrinter printer) {
		//throw new ToBeCompletedException("using the 'printer', print the products in this stock");
		this.stream()
				.forEach(p->printer.print(p));

	}
	
	public Map<String, List<Product>> groupByCategory() {
		//throw new ToBeCompletedException("Retrun a map of a stock of products grouped by the category");
		Map<String ,List<Product>> map=this.stream()
				.collect(Collectors.groupingBy(Product::getCategory));
		return map;
	}
	
	public Object findProduct(String name) {
		//throw new ToBeCompletedException("Look for a product having the name 'name' if found");
		Optional<Product> product =this.stream()
				.filter(p->p.getName().equals(name))
				.findFirst();
		product.ifPresent(p->System.out.println(p.getPrice()));
		return product;

	}
	
	public Stock moreExpensiveThan(Product product) {
		/*throw new ToBeCompletedException("return a new Stock of products" +
				" that are more expensive that a given product");*/
		Stock moreExpensive=this.stream()
				.filter(p->p.getPrice()>product.getPrice())
				.collect(Collectors.toCollection(Stock::new));
		return moreExpensive;
	}
	
	public Collection<Product> sorted() {
		return stream()
				.sorted(COMPARATOR)
				.collect(Collectors.toList());
	}
}
