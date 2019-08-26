package org.learnings.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaxTemperatureReducer extends Reducer<IntPair,IntWritable,IntPair,NullWritable> {

    @Override
    protected void reduce(IntPair key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }
}
