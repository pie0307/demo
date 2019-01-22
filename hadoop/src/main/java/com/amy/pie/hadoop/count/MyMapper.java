package com.amy.pie.hadoop.count;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2018/5/10 11:06
 */
class MyMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text word = new Text();
    protected void map(LongWritable key, Text value, Context context) throws java.io.IOException, InterruptedException {
        String[] text = value.toString().split("\t");

        word.set(text[0] + "-" + text[2]);
        context.write(word, new Text(""));
    }
}
