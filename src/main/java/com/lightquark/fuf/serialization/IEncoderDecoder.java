package com.lightquark.fuf.serialization;

import java.util.List;

/**
 * Created by Light Quark.
 */
public interface IEncoderDecoder
{
    <T> T decodeObject(byte... bytes);

    <T> List<T> decodeObjects(byte... bytes);

    byte[] encodeObject(Object object);

    byte[] encodeObjects(List<Object> objects);
}
