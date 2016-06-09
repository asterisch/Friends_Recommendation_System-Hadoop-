
package Mapred;
import relations.Friend_Tuple;
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.log4j.Logger;

/**
 *
 * @author asteriosc
 */
public class Map extends MapReduceBase implements Mapper<Text,Text,Text,Friend_Tuple> {

    Logger log = Logger.getLogger(Map.class);
    @Override
    public void map(Text user, Text friends, OutputCollector<Text, Friend_Tuple> output, Reporter rprtr) throws IOException {
        
        String usr = user.toString().trim();                    // Get USER's name
        String[] friendlist = friends.toString().split(", ");   // Get USER's friends 
        if(usr.length()>1){
        for(int i=0;i<friendlist.length;i++)
        {
            if (friendlist[i].length()>1)
            {
                output.collect(new Text(usr), new Friend_Tuple(friendlist[i],"null")); 
                // collect all friends of USER  => Emit <user,(friend,"null")>
                for(int j=i+1;j<friendlist.length;j++)
                {
                  // collect all possible combination of USER's friends 
                  if (friendlist[j].length()>1) 
                  {
                      output.collect(new Text(friendlist[i].trim()), new Friend_Tuple(friendlist[j].trim(),usr));
                      output.collect(new Text(friendlist[j].trim()), new Friend_Tuple(friendlist[i].trim(),usr));
                  }
                  // Emit<user1,(user2,common)> <user2,(user1,common).
                  // So as to, USER has a list in form <user,common_friends_with_USER>
                }
            }
        }
               
        }
        
        
    }

    

   
    
    
}
