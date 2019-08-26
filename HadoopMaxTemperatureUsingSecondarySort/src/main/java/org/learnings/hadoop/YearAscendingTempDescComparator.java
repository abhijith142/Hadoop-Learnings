package org.learnings.hadoop;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class YearAscendingTempDescComparator extends WritableComparator {
    public YearAscendingTempDescComparator() {
        super(IntPair.class,true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        IntPair ip1 = (IntPair) w1;
        IntPair ip2 = (IntPair) w2;

        int cmp = Integer.compare(ip1.getFirst(),ip2.getFirst());
        if(cmp != 0)
            return cmp;

        // descending for temperature
        return -Integer.compare(ip1.getSecond(),ip2.getSecond());
    }
}
