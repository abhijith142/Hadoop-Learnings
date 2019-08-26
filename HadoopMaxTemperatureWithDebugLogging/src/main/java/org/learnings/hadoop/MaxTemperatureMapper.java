package org.learnings.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class MaxTemperatureMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    public enum Temperature {
        OVER_100;
    }

    private NCDCRecordParser parser = new NCDCRecordParser();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value.toString());

        if(parser.isValidTemp()){
            if(parser.getAirTemperature() > 100){
                System.err.println("Temperature over 100 degrees for input " + value);
                context.setStatus("Detected possible corrupt record. see logs");
                context.getCounter(Temperature.OVER_100).increment(1);
            }
            context.write(new Text(parser.getYear()), new IntWritable(parser.getAirTemperature()));
        }
    }
}
