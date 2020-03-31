import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class Part4_3 {
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            String s = value.toString();
            int index_get = s.indexOf("\"");
            int index_start = s.indexOf(" ",index_get)+1;
            int index_end = s.indexOf(" ", index_start);
            String url = s.substring(index_start,index_end);
            word.set(url);
            context.write(word, one);
        }
    }

    public static class IntSumReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {
        private Text maxWord = new Text();
        private int max = 0;
        @Override
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            if (sum > max) {
                max = sum;
                maxWord.set(key);
            }
        }

        @Override
        public void cleanup(Context context) throws IOException, InterruptedException {
            context.write(maxWord, new IntWritable(max));
        }
    }

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        Configuration conf = new Configuration();


        Job job = Job.getInstance(conf, "part4third");
        job.setJarByClass(Part4_3.class);

        job.setMapperClass(Part4_3.TokenizerMapper.class);
        job.setCombinerClass(Part4_3.IntSumReducer.class);
        job.setReducerClass(Part4_3.IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        Path out_path = new Path(args[1]);
        FileSystem fileSystem = out_path.getFileSystem((conf));
        if (fileSystem.exists(out_path)) {
            fileSystem.delete(out_path,true);
        }
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
