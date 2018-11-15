package com.windows;

public class Heap {
	public static boolean heapExist = true;
	private int weightHeap = 100 ;
	private int weightOut;
	private int temp; 

	public int getWeight() {
		return weightHeap;
	}

	public int getWeightOut() {
		return weightOut;
	}

	public void setWeight(int weight) {
		weightOut = 0;
		if (weightHeap > 0 && weightHeap >= weight & weightHeap-weight>0) {
			weightHeap -= weight;
			weightOut += weight;
		} else {
			temp = weightHeap;
			weightHeap -= temp;
			weightOut = temp;
			heapExist = false;
		}
	}
}
