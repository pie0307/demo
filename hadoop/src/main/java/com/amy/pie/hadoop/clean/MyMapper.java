package com.amy.pie.hadoop.clean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Arrays;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2018/5/10 11:06
 */
class MyMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
    LogParser logParser = new LogParser();
    Text outputValue = new Text();

    protected void map(LongWritable key, Text value, Context context) throws java.io.IOException, InterruptedException {
        final String[] parsed = logParser.parse(value.toString());

        // step1.过滤掉静态资源访问请求
        if (parsed[2].startsWith("GET /static/")
                || parsed[2].startsWith("GET /uc_server")) {
            return;
        }
        // step2.过滤掉开头的指定字符串
        if (parsed[2].startsWith("GET /")) {
            parsed[2] = parsed[2].substring("GET /".length());
        } else if (parsed[2].startsWith("POST /")) {
            parsed[2] = parsed[2].substring("POST /".length());
        }
        // step3.过滤掉结尾的特定字符串
        if (parsed[2].endsWith(" HTTP/1.0")) {
            parsed[2] = parsed[2].substring(0, parsed[2].length()
                    - " HTTP/1.0".length());
        }
        if (parsed[2].contains("?")) {
            parsed[2] = parsed[2].substring(0, parsed[2].indexOf("?"));
        }
        // step4.只写入前三个记录类型项
        outputValue.set(parsed[0] + "\t" + parsed[1] + "\t" + parsed[2]);
        System.out.println(Arrays.toString(outputValue.getBytes()));
        context.write(key, outputValue);
    }
}
