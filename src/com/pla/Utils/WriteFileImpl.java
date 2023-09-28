package com.pla.Utils;

import java.io.File;
import java.io.FileOutputStream;

public class WriteFileImpl {
    public static void main(String[] args) throws Exception {
        String path = "/Users/computerJava/datas/tmp/hello.txt";
        File file = new File(path);
        String content = "hello, computerJava.\n";
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }
}
