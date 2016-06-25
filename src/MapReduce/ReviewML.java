package MapReduce;


import Parser.*;
import Property.*;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.codehaus.jackson.map.ObjectMapper;

public class ReviewML {
	public static class JsonMapper extends Mapper<Object, Text, IntWritable, ReivewWritable> {

		private ReivewWritable info = new ReivewWritable();
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			try {
				ObjectMapper mapper = new ObjectMapper();
	            String[] tuple = value.toString().split("\\n");
	             for(int i=0;i<tuple.length; i++)
	             {
	            	 Review review = mapper.readValue(tuple[i], Review.class);
	            	 
	            	 if(review.text != null)
	            	 {
	            		 info.SetName(review.text);
	            		 info.SetStars(review.stars);
	            		 if(Integer.parseInt(review.stars) > 3)
	            		 {
	            			 info.SetPosLabel(1);
	            			 info.SetNegLabel(0);
	            		 }
	            		 else
	            		 {
	            			 info.SetPosLabel(0);
	            			 info.SetNegLabel(1);
	            		 }
	            		 context.write(new IntWritable(i), info);
	            		
	            	 } 
	              }

			} catch (JSONException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();

			}
		}
		 public static void main(String[] args) throws Exception {
	    	 runJob(args[0], args[1]);
	    }
	 

	    
	    public static void runJob(String input, String output) throws Exception {
	    	
	        Configuration conf = new Configuration();
	        Job job = new Job(conf);
	        job.setJarByClass(ReviewML.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(InfoWritable.class);
	        job.setMapperClass(JsonMapper.class);
	        //job.setReducerClass(JsonReducer.class);
	        //job.setInputFormatClass(KeyValueTextInputFormat.class);
	        job.setNumReduceTasks(1);
	        job.setOutputFormatClass(TextOutputFormat.class);

	        FileInputFormat.setInputPaths(job, new Path(input));
	        Path outPath = new Path(output);
	        FileOutputFormat.setOutputPath(job, outPath);
	        outPath.getFileSystem(conf).delete(outPath, true);

	        job.waitForCompletion(true);
	    }
		
	}
}
