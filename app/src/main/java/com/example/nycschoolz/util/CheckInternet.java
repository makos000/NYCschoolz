package com.example.nycschoolz.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class CheckInternet {

    public boolean isInternetAvailable(String address, int port, int timeoutMs) {
        try {
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress(address, port);

            sock.connect(sockaddr, timeoutMs); // This will block no more than timeoutMs
            sock.close();

            return true;

        } catch (IOException e) { return false; }
    }
}
