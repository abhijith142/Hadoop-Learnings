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
    public static void main(String args[]) throws IOException, URISyntaxException {
        String uri = args[0];
        FSDataInputStream open = null;
        try {
            FileSystem fs = FileSystem.get(new URI(uri),new Configuration());
            open = fs.open(new Path(uri));
            IOUtils.copyBytes(open, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(open);
        }
    }
}
