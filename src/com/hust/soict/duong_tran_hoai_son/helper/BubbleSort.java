package com.hust.soict.duong_tran_hoai_son.helper;

public class BubbleSort {
	public void sort(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				if (arr[j] > arr[j + 1]) {
	                int tempSort = arr[j];
	                arr[j] = arr[j+1];
	                arr[j+1] = tempSort;
	            }
			}
		}
	}
}
