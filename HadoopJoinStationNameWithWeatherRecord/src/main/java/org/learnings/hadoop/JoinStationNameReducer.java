package org.learnings.hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinStationNameReducer extends Reducer<TextPair,Text,Text,Text> {

    @Override
    protected void reduce(TextPair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        Text stationName = iterator.next();
        while(iterator.hasNext()){
            Text record = iterator.next();
            Text outVal = new Text(stationName.toString() + "\t" + record.toString());
            context.write(key.getFirst(),outVal);
        }
    }
}
