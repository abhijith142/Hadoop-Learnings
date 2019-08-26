package org.learnings.hadoop;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
    public GroupComparator(){
        super(IntPair.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        IntPair ip1 = (IntPair) a;
        IntPair ip2 = (IntPair) b;

        return Integer.compare(ip1.getFirst(),ip2.getFirst());
    }
}
