package org.learnings.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Demo extends Configured implements Tool {
    public static void main(String []args) throws Exception {
        int exitCode = ToolRunner.run(new Demo(), args);
        System.exit(exitCode);
    }

    public int run(String[] args) throws Exception {
        long startTime = System.nanoTime();
        if(args.length != 2){
            System.err.println("Invalid usage");
            return -1;
        }

        Job job = new Job();
        job.setJarByClass(Demo.class);
        job.setJobName("Word counter");
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapperClass(MapClass.class);
        job.setReducerClass(ReduceClass.class);

        int returnVal = job.waitForCompletion(true) ? 0 : 1;
        long endTime = System.nanoTime();
        System.out.println("Time taken = " + (endTime-startTime));
        if(job.isSuccessful()){
            System.out.println("Job successful");
        } else {
            System.out.println("Job unsuccessful");
        }


        return returnVal;

    }
}
