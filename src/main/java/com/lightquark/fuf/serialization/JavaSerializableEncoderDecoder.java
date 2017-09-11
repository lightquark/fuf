package com.lightquark.fuf.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Light Quark.
 */
public class JavaSerializableEncoderDecoder implements IEncoderDecoder
{
    @SuppressWarnings("unchecked")
    @Override
    public <T> T decodeObject(byte... bytes)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(bais))
        {
            return (T) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> decodeObjects(byte... bytes)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(bais))
        {
            int objectCount = ois.readInt();
            List<T> objectList = new ArrayList<T>(objectCount);
            for (int i = 0; i < objectCount; i++)
            {
                objectList.add((T) ois.readObject());
            }
            return objectList;
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    @Override
    public byte[] encodeObject(final Object object)
    {
        return encodeObjects(Collections.singletonList(object));
        //return encodeObjects(Arrays.asList(object));
    }

    @Override
    public byte[] encodeObjects(List<Object> objects)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos))
        {
            oos.writeInt(objects.size());
            for (Object object : objects)
            {
                if (!(object instanceof Serializable))
                {
                    throw new RuntimeException("Trying to serializable non-serializable object");
                }
                oos.writeObject(object);
            }
            oos.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }
}
