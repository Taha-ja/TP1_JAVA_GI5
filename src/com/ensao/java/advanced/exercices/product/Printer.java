package com.ensao.java.advanced.exercices.product;

@FunctionalInterface
public interface Printer<T> {
	void print(T t);
}
