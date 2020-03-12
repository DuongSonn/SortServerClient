package com.hust.soict.duong_tran_hoai_son.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 9898);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(in.readLine());
			Scanner scanner = new Scanner(System.in);
			String message;
			do {
				System.out.print("Enter message: ");
				message = scanner.nextLine();
				// System.out.println(message);
				out.println(message);
				System.out.println(in.readLine());
			} while (!message.isEmpty());
			scanner.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
