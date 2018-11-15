package com.windows;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private boolean work = true;
	private Heap heap;
	private Cart cart;
	private Exchanger<Cart> ex1;
	public Thread loader;
	
	public Loader(Heap heap, Exchanger<Cart> ex1) {
		this.heap = heap;
		this.ex1 = ex1;
		loader = new Thread(this);
	}

	public void takeCart(Cart cart) {
		this.cart = cart;
		System.out.println("У Погрузчика есть " + cart.getNameCart());
	}

	@Override
	public void run() {
		System.out.println("Вес кучи: " + heap.getWeight());
		while (work) {
			while (cart.fullCart == false && heap.getWeight()>0) {
				System.out.println("Погрузчик начал загрузку");
				heap.setWeight(2);
				cart.loadCart(heap.getWeightOut());
				System.out.println("Вес кучи: " + heap.getWeight() + "кг");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("В тележке " + cart.getWeightCart() + "кг");
			}
			System.out.println("Погрузчик закончил загрузку");
			if (Heap.heapExist) {
			try {
				System.out.println("Погрузчик отдал тележку\n");
				cart = ex1.exchange(cart);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				cart = ex1.exchange(cart);
				System.out.println("У погрузчика есть " + cart.getNameCart());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			
			} else {
				work = false;
				try {
					System.out.println("Погрузчик отдал тележку\n");
					cart = ex1.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				
			}
		}
	}
}
