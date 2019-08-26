package org.learnings.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class SmallFilesToSequenceFileConverter extends Configured implements Tool {
    static class SmallFilesToSequenceFileMapper extends Mapper<NullWritable,BytesWritable,Text,BytesWritable>{
        private Text filenameKey;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            Path path = inputSplit.getPath();
            filenameKey = new Text(path.getName());
        }

        @Override
        protected void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {
            context.write(filenameKey,value);
        }
    }

    public int run(String[] args) throws Exception {
        if(args.length != 2)
        {
            System.err.println("Invalid arguments, input and output path required");
            System.exit(-1);
        }
        Job job = Job.getInstance(getConf(),"Small file to sequence file converter");

        job.setJarByClass(getClass());

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setInputFormatClass(WholeFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        job.setMapperClass(SmallFilesToSequenceFileMapper.class);

        return job.waitForCompletion(true)? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new SmallFilesToSequenceFileConverter(),args);
        System.exit(exitCode);
    }
}
