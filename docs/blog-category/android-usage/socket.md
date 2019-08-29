## socket  
客户端部分
```
 try {
            Socket socket = new Socket("localhost", 10086);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("输出示例");
            pw.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = br.readLine()) != null) {
//                LogUtil.d(TAG, "服务器返回：" + info);
            }

            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
```
服务端部分
```
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
```
