/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relations;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.log4j.Logger;

/**
 *
 * @author terry
 */
public class Friend_Tuple implements WritableComparable<Friend_Tuple>
{
    private String user;
    private String friend;
    private final String separator="-";
    private static Logger log= Logger.getLogger(Friend_Tuple.class);
    public Friend_Tuple()
    {
        this.user = new String();
        this.friend=new String();
    }
    public Friend_Tuple(String user,String common)
    {
            this.user=user.trim();
            this.friend=common.trim();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeBytes(user+separator+friend);
    }

    @Override
    public void readFields(DataInput in) throws IOException {

       {
           String temp [] = in.readLine().split(separator);
            this.user=temp[0];
            this.friend=temp[1];
       }

    }

    /**
     * @return the PersonA
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the PersonB
     */
    public String getFriend() {
        return friend;
    }

    @Override
    public int compareTo(Friend_Tuple other_tuple) {
        
        if( this.getUser().compareTo(other_tuple.getUser())==0)
        {
            return this.getFriend().compareTo(other_tuple.getFriend());
        }
        return this.getUser().compareTo(other_tuple.getUser());
        
    }

    @Override
    public int hashCode() {
        
        return user.hashCode()+friend.hashCode();
        
    }
    
    @Override
    public String toString()
    {
        return getUser()+"-"+getFriend();
    }

    /**
     * @param user the PersonA to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @param friend the PersonB to set
     */
    public void setFriend(String friend) {
        this.friend = friend;
    }
}