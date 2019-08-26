import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class Demo {
    public static void main(String args[]) throws IOException {
        String uri = args[0];
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.newInstance(URI.create(uri),configuration);

        FileStatus[] fileStatuses = fs.listStatus(new Path(uri));
        for(FileStatus status: fileStatuses){
            System.out.println(status.getPath());
        }

    }
}
