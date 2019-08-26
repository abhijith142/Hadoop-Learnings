package org.learnings.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

public class JobBuilder {
    public static Job parseInputAndOutput(Tool tool, Configuration conf, String[] args) throws IOException {
        Job job = Job.getInstance(conf);
        job.setJarByClass(tool.getClass());

        if(args.length != 2){
            System.err.println("Invalid usage please provide input and output paths");
            return null;
        }

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        return job;
    }
}
