/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relations;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 *
 * @author terry
 */
public class Comparator extends WritableComparator
{
    protected Comparator()
    {
        super(Friend_Tuple.class,true);
    }
    @Override
    public int compare(WritableComparable obj1,WritableComparable obj2)
    {
        Friend_Tuple ft1 = (Friend_Tuple) obj1;
        Friend_Tuple ft2 = (Friend_Tuple) obj2;
        return ft1.compareTo(ft2);
    }
}
