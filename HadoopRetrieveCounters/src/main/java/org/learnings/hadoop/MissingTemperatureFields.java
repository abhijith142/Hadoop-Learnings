package org.learnings.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Cluster;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MissingTemperatureFields extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new MissingTemperatureFields(),args);
        System.exit(exitCode);

    }

    public int run(String[] args) throws Exception {

        if(args.length != 1){
            System.err.println("Invalid usage");
            return -1;
        }

        String jobId = args[0];
        Cluster cluster = new Cluster(getConf());
        Job job = cluster.getJob(JobID.forName(jobId));
        if(job == null){
            System.err.println("Invalid job id");
            return -1;
        }

        if(!job.isComplete()){
            System.err.println("Job is not complete");
            return -1;
        }

        Counters counters = job.getCounters();
        long missing = counters.findCounter(MaxTemperatureMapper.Temperature.MALFORMED).getValue();

        System.out.println("Missing counter " + missing);
        return 0;
    }
}
