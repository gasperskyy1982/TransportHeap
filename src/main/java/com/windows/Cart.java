package com.windows;

public class Cart {
	private String name = "Тележка";
	public boolean fullCart = false;
	public boolean emptyCart = true;
	private int weightCart = 0;
	private int weightOut = 0;
	private int maxCapacity = 6;

	public int getWeightCart() {
		return weightCart;
	}

	public String getNameCart() {
		return name;
	}

	public void loadCart(int weight) {
		if (weightCart < maxCapacity) {
			if (weightCart + weight < maxCapacity) {
				weightCart += weight;
				weightOut = weight;
				fullCart = false;
				emptyCart = false;
			} else {
				 weightCart += weight;
				fullCart = true;
				emptyCart = false;
			}
		} else {
			fullCart = true;
			emptyCart = false;
		}
	}

	public int unloadCart(int weight) {
		if (weightCart > 0) {
			if (weightCart - weight > 0) {
				weightCart -= weight;
				emptyCart = false;
				return weight;
			} else {
				weight = weightCart;
				weightCart -= weight;
				emptyCart = true;
				fullCart = false;
				return weight;
			}
		} else {
			emptyCart = true;
			fullCart = false;
			return weightCart;
		} 
	}

}
