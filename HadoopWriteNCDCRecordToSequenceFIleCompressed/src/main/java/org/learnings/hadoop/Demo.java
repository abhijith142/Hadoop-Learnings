package org.learnings.hadoop;

import org.apache.hadoop.conf.Configured;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Demo extends Configured implements Tool {
    public int run(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("Invalid usage");
            return -1;
        }
        Job job = Job.getInstance(getConf());
        job.setJarByClass(getClass());

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));


        job.setMapperClass(CleanerMapper.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(0);

        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        return job.waitForCompletion(true)?0:1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Demo(),args);
        System.exit(exitCode);
    }
}
