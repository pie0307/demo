package com.amy.pie.hadoop.clean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2018/5/10 11:14
 */
public class LogCleanJob extends Configured implements Tool {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            int res = ToolRunner.run(conf, new LogCleanJob(), args);
            System.exit(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        final Job job = new Job(new Configuration(),
                LogCleanJob.class.getSimpleName());
        // 设置为可以打包运行
        job.setJarByClass(LogCleanJob.class);
        FileInputFormat.setInputPaths(job, args[0]);
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);

        //7分区(默认1个)，排序，分组，规约 采用 默认
        job.setCombinerClass(MyReducer.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // 清理已存在的输出文件
        FileSystem fs = FileSystem.get(new URI(args[0]), getConf());
        Path outPath = new Path(args[1]);
        if (fs.exists(outPath)) {
            fs.delete(outPath, true);
        }

        boolean success = job.waitForCompletion(true);
        if (success) {
            System.out.println("Clean process success!");
        } else {
            System.out.println("Clean process failed!");
        }
        return 0;
    }
}
