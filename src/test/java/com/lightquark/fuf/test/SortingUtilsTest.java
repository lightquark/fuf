package com.lightquark.fuf.test;

import com.lightquark.fuf.alg.SortingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Light Quark.
 */
public class SortingUtilsTest
{
    private static final Logger LOG = LogManager.getLogger();

    private int[] makeArray()
    {
        Random r = new Random();
        int ARRAY_SIZE = 1000;
        int[] a = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++)
        {
            a[i] = r.nextInt(100);
        }
        return a;
    }

    private void checkArray(int[] a)
    {
        LOG.debug("Check sorted array");
        for (int i = 0; i < a.length - 1; i++)
            Assert.assertTrue(a[i] <= a[i + 1]);
    }

    @Test
    public void testQuickSort()
    {
        //test bubble sort
        int[] arr1 = makeArray();
        SortingUtils.quickSort(arr1);
        //LOG.debug("sorted array = " + CommonUtils.convertToString(arr1));
        checkArray(arr1);

        //test bubble sort
        int[] arr2 = makeArray();
        SortingUtils.quickSort(arr2);
        checkArray(arr2);

        //test quick sort
        int[] arr3 = makeArray();
        SortingUtils.quickSort(arr3);
        checkArray(arr3);

        //test merge sort
        int[] arr4 = makeArray();
        SortingUtils.quickSort(arr4);
        checkArray(arr4);
    }
}
