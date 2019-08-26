package org.learnings.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxTemperatureMapper extends Mapper<LongWritable,Text,IntPair,NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        NCDCRecordParser parser = new NCDCRecordParser();
        parser.parse(value.toString());
        if(parser.isValidTemp()){
            context.write(new IntPair(parser.getYearInt(), parser.getAirTemperature()), NullWritable.get());
        }
    }
}
