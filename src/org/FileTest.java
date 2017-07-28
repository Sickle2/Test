package org;

import java.io.*;

/**
 * Created by sickle on 17-7-28.
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf=new RandomAccessFile("newfile.txt","rw");
        //用读写的方式创建随机链接文件类
        File temp=File.createTempFile("test",".txt");
        //创建临时文件
        FileWriter fw=new FileWriter(temp);
        raf.seek(11);
        //定位文件下次读写位置（只有一次有效）
        fw.write(raf.readLine());
        raf.seek(11);
        raf.writeBytes("I am xxx");
        fw.flush();
        fw.close();
        //要关掉读写别人才能继续操作
        FileReader fr=new FileReader(temp);
        BufferedReader buf=new BufferedReader(fr);
        raf.writeBytes(buf.readLine());
        raf.close();
        buf.close();
        temp.deleteOnExit();  //临时文件在结束时删除
    }
}
