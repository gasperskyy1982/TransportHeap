package com.windows;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransportDemo {

	public static void main(String[] args) {
		System.out.println("Start");
		Exchanger<Cart> ex1 = new Exchanger<>();
		Exchanger<Cart> ex2 = new Exchanger<>();
		Heap heap = new Heap();
		Cart cart = new Cart();
				
		Sandpiper sand = new Sandpiper(ex1, ex2);
		Loader loader = new Loader(heap, ex1);
		loader.takeCart(cart);
		loader.loader.start();
		
		Unloader unloader = new Unloader(ex2);
		
		
		
	}

}
