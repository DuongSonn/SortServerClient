package com.hust.soict.duong_tran_hoai_son.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import com.hust.soict.duong_tran_hoai_son.helper.*;

public class Server {
	public static void main(String[] args) {
		System.out.println("The Sorter server is running");
		int clientNumber = 0;
		try (ServerSocket listener = new ServerSocket(9898)) {
			while (true) {
				new Sorter(listener.accept(), clientNumber++).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static class Sorter extends Thread {
		private Socket socket;
		private int clientNumber;
		
		public Sorter(Socket socket, int clientNumber) {
			this.socket = socket;
			this.clientNumber = clientNumber;
			System.out.println("Number client #" + clientNumber + " connected at " + socket);
		}
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println("Hello, you are client #" + clientNumber);
				while (true) {
					String input = in.readLine();
					System.out.println(input);
					if  (input == null || input.isEmpty()) {
						break;
					}
					String[] nums =  input.split(" ");
					int[] intarr = new int[nums.length];
					int i = 0;
					for (String textValue : nums) {
						intarr[i] = Integer.parseInt(textValue);
						i++;
					}
					new ShellSort().sort(intarr);
					String strArray[] = Arrays.stream(intarr).mapToObj(String::valueOf).toArray(String[]::new);
					out.println(Arrays.toString(strArray));
				}
			} catch (Exception e) {
				System.out.println("Error handling client #" + clientNumber);
			} finally {
				try {
					socket.close();
				} catch (Exception e2) {
					System.out.println("Connection with client # " + clientNumber + " closed");
				}
			}
		}
	}
}
