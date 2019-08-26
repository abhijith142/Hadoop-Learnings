package org.learnings.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map;

public class Demo extends Configured implements Tool {

    static {
        //Configuration.addDefaultResource("hdfs-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
    }

    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        for(Map.Entry<String,String> entry : conf){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Demo(),args);
        System.exit(exitCode);
    }
}
