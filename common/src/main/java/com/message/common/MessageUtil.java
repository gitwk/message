package com.message.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author wangkai
 * @since JDK8
 */
public class MessageUtil {

    static Scanner scanner = new Scanner(System.in);

    public static String receiveMessage(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine();
    }

    public static String generateMessage() {
        StringBuilder sb = new StringBuilder();
        String input;
        while (!"发送".equals(input = scanner.nextLine())) {
            sb.append(input);
        }
        return sb.toString();
    }

}
