/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapred;
import relations.Friend_Tuple;
import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.log4j.Logger;

/**
 *
 * @author terry
 */
public class Map extends MapReduceBase implements Mapper<Text,Text,Text,Text> {

    private final String friendsof=" -> ";
    //private final String endoflist=" -EOL- ";
    private final String separator="-";
    private Friend_Tuple ft;
    @Override
    public void map(Text user, Text friends, OutputCollector<Text, Text> output, Reporter rprtr) throws IOException {
        
        String usr = user.toString().trim();
        String[] friendlist = friends.toString().split(",");
        
        StringBuilder friends_string = new StringBuilder();
        StringBuffer sb= new StringBuffer();
        for(String name :friendlist)
        {
            if (name.length()>1) sb.append(name.trim()).append(" ");
               
        }
        friends_string.append(sb);
        for(String friend_name:friendlist)
        {
             Logger log = Logger.getLogger(Map.class); 
             log.info(friend_name+"-> "+friends_string.toString()+friendsof+usr);
            // log.debug("=================================================||========================================");
           if (friend_name.length()>1) output.collect( new Text(friend_name.trim()),new Text(friends_string.toString()+friendsof+usr) );
        }
        output.collect(new Text(usr),new Text(friends_string.toString()+friendsof+usr));
     }
        
    }

    

   
    
    

