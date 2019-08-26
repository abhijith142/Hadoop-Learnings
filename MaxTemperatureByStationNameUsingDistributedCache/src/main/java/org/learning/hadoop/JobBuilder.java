package org.learning.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

public class JobBuilder {
    public static Job parseInputAndOutput(Tool tool, Configuration conf,
                                          String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Invalid usage");
            return null;
        }
        Job job = Job.getInstance(conf,"Max Temperature with Station Name using Distributed Cache");
        job.setJarByClass(tool.getClass());
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        return job;
    }
}
