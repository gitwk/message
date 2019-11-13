package com.message.server.io;

import com.message.common.MessageUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangkai
 * @since JDK8
 */
public class Server1 {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9090);

        while (true) {
            Socket socket = serverSocket.accept();

            System.out.println("connection established: " + socket.getPort());

            String receiveMsg = MessageUtil.receiveMessage(socket.getInputStream());
            System.out.println("receive from client : " + receiveMsg);

            System.out.println("tell to client：");
            String sendMessage = MessageUtil.generateMessage();
            socket.getOutputStream().write(sendMessage.getBytes());
            socket.shutdownOutput();

            System.out.println();
        }

    }

}
