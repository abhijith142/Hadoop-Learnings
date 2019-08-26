package org.learnings.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class Demo {
    public static void main(String args[]) throws IOException, URISyntaxException {
        String localsrc = args[0];
        String dest = args[1];
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(localsrc));

        FileSystem fs = FileSystem.get(new URI(dest),new Configuration());
        FSDataOutputStream os = fs.create(new Path(dest));

        IOUtils.copyBytes(in,os,4096,false);

    }
}
