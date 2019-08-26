package org.learnings.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CleanerMapper extends Mapper<LongWritable,Text,IntWritable,Text> {
    NCDCRecordParser parser = new NCDCRecordParser();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value.toString());
        if(parser.isValidTemp()){
            context.write(new IntWritable(parser.getAirTemperature()),value);
        }
    }
}
