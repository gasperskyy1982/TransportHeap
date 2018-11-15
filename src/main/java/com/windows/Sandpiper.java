package com.windows;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Sandpiper implements Runnable {
	private boolean work = true;
	private Cart cart;
	private Exchanger<Cart> ex1;
	private Exchanger<Cart> ex2;

	public Sandpiper(Exchanger<Cart> ex1, Exchanger<Cart> ex2) {
		this.ex1 = ex1;
		this.ex2 = ex2;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (work) {
			try {
				cart = ex1.exchange(cart);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			System.out.println("У перевозчика есть " + cart.getNameCart());
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (Heap.heapExist) {
				try {
					System.out.println("Перевозчик отвез тележку разгрузчику\n");
					ex2.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					ex2.exchange(cart);
					System.out.println("У перевозчика есть " + cart.getNameCart());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				try {
					System.out.println("Перевозчик отвез тележку погрузчику\n");
					cart = ex1.exchange(cart);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
			} else {
				work = false;
				try {
					System.out.println("Перевозчик отвез тележку разгрузчику\n");
					ex2.exchange(cart);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}
}
