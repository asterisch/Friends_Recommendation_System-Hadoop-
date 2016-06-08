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
    private Text PersonA;
    private Text PersonB;
    private Text separator;
    private static Logger log= Logger.getLogger(Friend_Tuple.class);
    public Friend_Tuple()
    {
        this.separator = new Text("-");
        this.PersonA = new Text();
        this.PersonB=new Text();
    }
    public Friend_Tuple(String persons_concated)
    {
        this.separator = new Text("-");
        String temp [] = persons_concated.split(String.valueOf(this.separator));
        this.PersonA=new Text(temp[0]);
        this.PersonB=new Text(temp[1]);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        //out.writeBytes(this.getPersonA().toString()+this.separator+this.getPersonB().toString());
        PersonA.write(out);
        PersonB.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        /*String con_pers = in.readLine();
        String temp [] = con_pers.split(String.valueOf(separator));
        this.setPersonA(temp[0]);
        this.setPersonB(temp[1]);*/
        PersonB.readFields(in);
        PersonA.readFields(in);
    }

    /**
     * @return the PersonA
     */
    public Text getPersonA() {
        return PersonA;
    }

    /**
     * @return the PersonB
     */
    public Text getPersonB() {
        return PersonB;
    }

    @Override
    public int compareTo(Friend_Tuple other_tuple) {
        
        
        
        if( this.getPersonA().toString().compareTo(other_tuple.getPersonB().toString())==0)
        {
            int yes = this.getPersonB().toString().compareTo(other_tuple.getPersonA().toString());
            if (yes==0)
            {
              log.info(this.PersonA.toString()+"-"+this.PersonB.toString());
             // log.info(other_tuple.getPersonB().toString()+"-"+other_tuple.getPersonA().toString());  
            }
            return yes;
        }
        log.info(this.PersonA.toString()+"-"+this.PersonB.toString());
        log.info(other_tuple.getPersonA().toString()+"-"+other_tuple.getPersonB().toString());
        log.info("FUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCK YOUUUU");
        return this.getPersonA().toString().compareTo(other_tuple.getPersonB().toString());
        
    }
   @Override
    public boolean equals(Object bj)
    {
        if (this == bj ) return true;
        if (bj == null ) return false;
        if (!(bj instanceof Friend_Tuple)) return false;
        Friend_Tuple other_tuple = (Friend_Tuple) bj;
        if (this.getPersonA().toString().trim().equals(other_tuple.getPersonB().toString().trim()))
        {
            if (this.getPersonB().toString().trim().equals(other_tuple.getPersonA().toString().trim()))
            {
                return true; 
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        
        int ascii=0;
        byte[] a = this.getPersonA().getBytes();
        byte[] b = this.getPersonB().getBytes();
        int i;
        for(i=0;i<a.length;i++)
        {
            ascii+=a[i];
        }
        //ascii.append((byte) this.separator.charAt(0));
        for(i=a.length;i<b.length;i++)
        {
            ascii+=b[i-a.length];
        }
        return ascii;/*
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.PersonA);
        hash = 29 * hash + Objects.hashCode(this.PersonB);
        return hash;*/
        
    }
    
    @Override
    public String toString()
    {
        return getPersonA().toString().trim()+separator+getPersonB().toString().trim();
    }

    /**
     * @param PersonA the PersonA to set
     */
    public void setPersonA(String PersonA) {
        this.PersonA.set(PersonA);
    }

    /**
     * @param PersonB the PersonB to set
     */
    public void setPersonB(String PersonB) {
        this.PersonB .set(PersonB);
    }
}