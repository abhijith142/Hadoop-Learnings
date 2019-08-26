package org.learnings.hadoop;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class StationMapper extends Mapper<LongWritable,Text,Text,Text> {
    NCDCRecordParser parser = new NCDCRecordParser();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value.toString());
        context.write(new Text(parser.getStationId()),value);
    }
}
