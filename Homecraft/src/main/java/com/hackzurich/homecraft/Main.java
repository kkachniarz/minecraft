package com.hackzurich.homecraft;

import java.io.IOException;

public class Main {
	public static void main(String [] args) {
		try {
			HomegateManager.getData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}