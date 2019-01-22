package com.amy.pie.hadoop.count;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2018/5/10 11:12
 */
public class MyReducer extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text k2,
                       Iterable<Text> dataBean,
                       Context context) throws IOException, InterruptedException {
        context.write(k2, new Text(""));
    }
}
