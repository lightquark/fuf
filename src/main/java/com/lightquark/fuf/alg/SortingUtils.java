package com.lightquark.fuf.alg;

/**
 * Created by Light Quark.
 */
public class SortingUtils
{
    /**
     * Sorts the specified array into ascending order.
     * Implements bubblesort algorithm
     */
    public static void bubbleSort(int[] a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = 1; j < (n - i); j++)
            {
                if (a[j - 1] > a[j])
                    swap(a, j - 1, j);
            }
        }
    }

    /**
     * Sorts the specified array into ascending order.
     * Implements bubblesort algorithm with a check for early termination
     */
    public static void bubbleSort2(int[] a)
    {
        int n = a.length;
        boolean swapped = true;
        while (swapped)
        {
            swapped = false;
            for (int i = 1; i < n; i++)
            {
                if (a[i - 1] > a[i])
                {
                    swap(a, i - 1, i);
                    swapped = true;
                }
            }
        }
    }

    /**
     * Sorts the specified array into ascending order.
     * Implements traditional quicksort algorithm
     */
    public static void quickSort(int[] a)
    {
        _quickSort(a, 0, a.length - 1);
    }

    private static void _quickSort(int[] a, int lower, int higher)
    {
        int i = lower;
        int j = higher;

        //Pivot is the middle of a range
        int pivot = a[lower + (higher - lower) / 2];

        // Divide into two ranges
        while (i <= j)
        {
            //Element which is greater than the pivot
            while (a[i] < pivot)
                i++;

            //Element which is less than the pivot
            while (a[j] > pivot)
                j--;

            //swap elements and search again
            if (i <= j)
            {
                swap(a, i, j);
                i++;
                j--;
            }
        }

        // call _quickSort() recursively for two ranges
        if (lower < j)
            _quickSort(a, lower, j);
        if (i < higher)
            _quickSort(a, i, higher);
    }

    /**
     * Sorts the specified array into ascending order.
     * Implements traditional mergesort algorithm
     */
    public static void mergeSort(int[] a)
    {
        int[] t = new int[a.length];
        _mergeSort(a, t, 0, a.length - 1);
    }

    private static void _mergeSort(int[] a, int[] t, int lower, int higher)
    {
        if (lower < higher)
        {
            int middle = lower + (higher - lower) / 2;

            // Sort the left and right range
            _mergeSort(a, t, lower, middle);
            _mergeSort(a, t, middle + 1, higher);

            for (int i = lower; i <= higher; i++)
                t[i] = a[i];

            int i = lower;
            int j = middle + 1;
            int k = lower;
            while (i <= middle && j <= higher)
            {
                if (t[i] <= t[j])
                {
                    a[k] = t[i];
                    i++;
                }
                else
                {
                    a[k] = t[j];
                    j++;
                }
                k++;
            }

            while (i <= middle)
            {
                a[k] = t[i];
                k++;
                i++;
            }
        }
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    private static void swap(int[] a, int i, int j)
    {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
}
