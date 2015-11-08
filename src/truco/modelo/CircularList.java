package truco.modelo;

import java.util.ArrayList;

/**
 * Created by Eze Cruz Avila on 07/11/2015.
 */
@SuppressWarnings("ALL")
public class CircularList<E> extends ArrayList<E>{

    @Override
    public E get(int index)
    {
        if(index < 0)
            index = index + size();

        return super.get(index);
    }
}
