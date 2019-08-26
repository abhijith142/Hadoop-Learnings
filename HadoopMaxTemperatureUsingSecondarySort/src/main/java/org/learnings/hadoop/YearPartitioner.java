package org.learnings.hadoop;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class YearPartitioner extends Partitioner<IntPair,NullWritable> {
    public int getPartition(IntPair intPair, NullWritable nullWritable, int numPartitions) {
        return Math.abs(intPair.getFirst() * 127) % numPartitions;
    }
}
