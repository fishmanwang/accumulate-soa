package com.accumulate.utils;

import org.springframework.util.FastByteArrayOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Object 工具类
 * Created by tjwang on 2017/1/5.
 */
public class ObjectUtils {

    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return ObjectMapperUtil.writePretty(obj);
    }

    public static <T> T clone(T obj) throws CloneNotSupportedException {
        T result = null;
        try {
            // Write the object out to a byte array
            FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(fbos);
            out.writeObject(obj);
            out.flush();
            out.close();

            // Retrieve an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());

            result = (T)in.readObject();
        } catch (IOException e) {
            // ignore
        } catch (ClassNotFoundException cnfe) {
            // ignore
        }
        return result;
    }

}
