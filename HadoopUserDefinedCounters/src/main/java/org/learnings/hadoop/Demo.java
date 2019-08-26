package org.learnings.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class Demo extends Configured implements Tool {
    public int run(String[] args) throws Exception {
        Job job = JobBuilder.parseInputAndOutput(this,getConf(),args);
        if(job == null){
            return -1;
        }

        job.setMapperClass(MaxTemperatureMapper.class);
        job.setCombinerClass(MaxTemperatureReducer.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        return job.waitForCompletion(true)?0:1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Demo(), args);
        System.exit(exitCode);
    }
}
