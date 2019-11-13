package com.message.server;

import com.message.common.MessageUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wangkai
 * @since JDK8
 */
public class Client1 {

    public static void main(String[] args) throws Exception {
        int size = 1;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(size);

        for (int i = 0; i < size; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    try {
                        go();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void go() throws IOException {
        while (true) {
            Socket socket = new Socket(InetAddress.getLocalHost(), 9090);

            System.out.println("tell to server：");
            String sendMessage = MessageUtil.generateMessage();
            socket.getOutputStream().write(sendMessage.getBytes());
            socket.getOutputStream().flush();
            socket.shutdownOutput();

            String receiveMsg = MessageUtil.receiveMessage(socket.getInputStream());
            System.out.println("receive from server : " + receiveMsg);

            socket.close();
        }

    }

}
