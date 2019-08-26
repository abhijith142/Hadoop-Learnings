package org.learnings.hadoop;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntPair implements WritableComparable<IntPair> {
    private int first;
    private int second;

    public IntPair() {
    }

    public IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public int compareTo(IntPair intPair) {
        int cmp = Integer.compare(first,intPair.getFirst());
        if(cmp != 0)
            return cmp;
        return Integer.compare(second,intPair.getSecond());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(first);
        dataOutput.writeInt(second);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        first = dataInput.readInt();
        second = dataInput.readInt();
    }

    @Override
    public int hashCode() {
        return first * 163 + second;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IntPair) {
            IntPair intPair = (IntPair) o;
            return first == intPair.getFirst() && second == intPair.getSecond();
        }
        return false;
    }
    @Override
    public String toString() {
        return first + "\t" + second;
    }
}
