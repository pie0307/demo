package com.amy.pie.hadoop.clean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Arrays;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2018/5/10 11:12
 */
public class MyReducer extends Reducer<LongWritable, Text, Text, NullWritable> {

    public void reduce(LongWritable k2,
                       Iterable<Text> v2s,
                       Context context) throws IOException, InterruptedException {
        for (Text v2 : v2s) {
            System.out.println(Arrays.toString(v2.getBytes()));
            context.write(v2, NullWritable.get());
        }
    }
}
