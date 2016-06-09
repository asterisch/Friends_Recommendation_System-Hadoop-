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
public class Map extends MapReduceBase implements Mapper<Text,Text,Text,Friend_Tuple> {

    Logger log = Logger.getLogger(Map.class);
    @Override
    public void map(Text user, Text friends, OutputCollector<Text, Friend_Tuple> output, Reporter rprtr) throws IOException {
        
        String usr = user.toString().trim();
        String[] friendlist = friends.toString().split(", ");
        if(usr.length()>1){
        for(int i=0;i<friendlist.length;i++)
        {
            if (friendlist[i].length()>1)
            {
                output.collect(new Text(usr), new Friend_Tuple(friendlist[i],"null")); // send to usr all his friends at form (user,-1) => all users are friend with usr
                for(int j=i+1;j<friendlist.length;j++)
                {
                  if (friendlist[j].length()>1) 
                  {
                      output.collect(new Text(friendlist[i].trim()), new Friend_Tuple(friendlist[j].trim(),usr));
                      output.collect(new Text(friendlist[j].trim()), new Friend_Tuple(friendlist[i].trim(),usr));
                  }
                }
            }
        }
               
        }
        
        
    }

    

   
    
    
}
