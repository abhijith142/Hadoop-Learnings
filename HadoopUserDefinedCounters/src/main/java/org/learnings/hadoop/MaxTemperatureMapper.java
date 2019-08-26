package org.learnings.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxTemperatureMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    public enum Temperature {
        MALFORMED,
        MISSING
    }

    private NCDCRecordParser parser = new NCDCRecordParser();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value.toString());

        if(parser.isValidTemp()){
            context.write(new Text(parser.getYear()),new IntWritable(parser.getAirTemperature()));
        } else if(parser.isAirTemperatureMalformed()){
            context.getCounter(Temperature.MALFORMED).increment(1);
        } else if(parser.isMissingTemperature()){
            context.getCounter(Temperature.MISSING).increment(1);
        }

        context.getCounter("TemperatureQuality",parser.getQuality()).increment(1);
    }
}
