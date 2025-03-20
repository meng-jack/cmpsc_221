// Jiaming (Jack) Meng
//
// Authored on Month Date, Year
package twodimdrawingapp;

public class Value<K>
{
    public K value;

    public Value(K initial)
    {
        value=initial;
    }

    public void set(K v)
    {
        value=v;
    }

    public K get()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return value==null?"null":value.toString();
    }
}
