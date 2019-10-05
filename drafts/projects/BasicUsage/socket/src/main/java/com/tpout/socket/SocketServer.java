package com.tpout.socket;

import com.yourena.tpout.librarys.util.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private String TAG = getClass().getSimpleName();

    public void sample() {

        try {
            ServerSocket serverSocket = new ServerSocket(10086);
            //上述代码执行解释之后还是处于阻塞状态，
            while (true) {
                //只有成功之后，才会调用accept
                Socket socket = serverSocket.accept();

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String info = null;
                while ((info = br.readLine()) != null) {
                    LogUtil.d(TAG, "客户端说：" + info);
                }
                socket.shutdownInput();

                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("好的");
                pw.flush();

                pw.close();
                os.close();
                br.close();
                isr.close();
                is.close();
                socket.close();
            }
//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
