import org.apache.hadoop.conf.Configured;import org.apache.hadoop.fs.Path;import org.apache.hadoop.io.Text;import org.apache.hadoop.mapred.FileInputFormat;import org.apache.hadoop.mapred.JobClient;import org.apache.hadoop.mapred.JobConf;import org.apache.hadoop.mapred.FileOutputFormat;import org.apache.hadoop.mapred.KeyValueTextInputFormat;import org.apache.hadoop.util.Tool;import org.apache.hadoop.util.ToolRunner;import relations.Friend_Tuple;public class Friend_suggestion extends Configured implements Tool{    @Override    public int run(String[] argv) throws Exception {        if (argv.length!=2)        {            System.out.println("Usage: program input output\n");            return -1;        }        JobConf conf = new JobConf(Friend_suggestion.class);                conf.setJobName("Friend suggest");        FileInputFormat.addInputPath(conf, new Path(argv[0]));        FileOutputFormat.setOutputPath(conf,new Path(argv[1]));                conf.setMapperClass(Mapred.Map.class);                conf.setReducerClass(Mapred.Reduce.class);                        conf.setMapOutputKeyClass(Text.class);        conf.setMapOutputValueClass(Friend_Tuple.class);                conf.setOutputKeyClass(Text.class);        conf.setOutputValueClass(Text.class);        conf.setInputFormat(KeyValueTextInputFormat.class);        conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator"," ");                JobClient.runJob(conf);                        return 0;    }    public static void main(String argv[]) throws Exception    {        int code = ToolRunner.run(new Friend_suggestion(), argv);        System.exit(code);    }    }