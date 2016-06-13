
package relations;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;


/**
 *
 * @author asteriosc
 */
public class Friend_tuple implements WritableComparable<Friend_tuple>
{
    private String user;
    private String friend;
    private final String separator="-";

    public Friend_tuple()
    {
        this.user = new String();
        this.friend=new String();
    }
    public Friend_tuple(String user,String common)
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
    public int compareTo(Friend_tuple other_tuple) {
        
        if( this.getUser().compareTo(other_tuple.getUser())==0)
        {
            return this.getFriend().compareTo(other_tuple.getFriend());
        }
        return this.getUser().compareTo(other_tuple.getUser());
        
    }
}