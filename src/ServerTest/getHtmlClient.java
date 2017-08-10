package ServerTest;

import java.io.*;
import java.net.Socket;
import java.awt.Desktop;

/**
 * Created by sickle on 17-8-6.
 */
public class getHtmlClient {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",30001);
        PrintStream bw=new PrintStream(socket.getOutputStream());
        bw.println("/home/sickle/test.html");
        bw.flush();

        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        File file=new File("/home/sickle/tem.html");
        FileWriter fileWriter=new FileWriter(file);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        String string=null;
        while ((string=br.readLine())!=null){
            bufferedWriter.write(string);
            bufferedWriter.write("\n");
            bufferedWriter.flush();
        }
        Desktop dp=Desktop.getDesktop();
        dp.open(new File("/home/sickle/tem.html"));
        if(bw!=null)
            bw.close();
        if(br!=null)
            br.close();
        if (bufferedWriter!=null)
            bufferedWriter.close();
        if (socket!=null)
            socket.close();
    }
}
