package org.learnings.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;


import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String codecClassName = args[0];
        Class<?> codecClass = Class.forName(codecClassName);
        Configuration conf = new Configuration();
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);

        CompressionOutputStream outputStream = codec.createOutputStream(System.out);

        IOUtils.copyBytes(System.in,outputStream,4096,false);
        outputStream.flush();

    }
}
