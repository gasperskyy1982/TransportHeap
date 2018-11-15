package com.windows;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {
	private boolean work = true;
	private Cart cart;
	private Exchanger<Cart> ex2;

	public Unloader(Exchanger<Cart> ex2) {
		this.ex2 = ex2;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (work) {
			try {
				cart = ex2.exchange(cart);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("У разгрузчика есть " + cart.getNameCart());
			
			while (cart.emptyCart != true) {
				System.out.println("В тележке: " + cart.getWeightCart() + "кг");
				cart.unloadCart(3);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Разгрузчик закончил разгрузку");
			System.out.println("Вес тележки " + cart.getWeightCart());
			if (Heap.heapExist) {
				try {
					cart = ex2.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				work = false;
				System.out.println("Работа сделана! По домам!");
			}
		}
	}

}
