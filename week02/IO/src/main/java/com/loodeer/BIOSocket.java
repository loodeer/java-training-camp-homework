package com.loodeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author luzuheng
 * @since 2021-06-29 09:57
 */
public class BIOSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println("step1: new ServerSocket ");
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("step2: client\t" + client.getPort());

            new Thread(() -> {
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        System.out.println(reader.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
