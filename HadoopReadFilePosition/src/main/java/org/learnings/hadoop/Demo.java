package org.learnings.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Demo {
    public static void main(String args[]) throws URISyntaxException, IOException {
        String uri = args[0];
        FileSystem fs = FileSystem.get(new URI(uri), new Configuration());

        FSDataInputStream in = fs.open(new Path(uri));
        try{
            IOUtils.copyBytes(in,System.out,4096,false);
            in.seek(0);
            IOUtils.copyBytes(in,System.out,4096,false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
