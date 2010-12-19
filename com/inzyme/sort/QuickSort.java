/*
 * @(#)QSortAlgorithm.java	1.3   29 Feb 1996 James Gosling
 *
 * Copyright (c) 1994-1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL or COMMERCIAL purposes and
 * without fee is hereby granted. 
 * Please refer to the file http://www.javasoft.com/copy_trademarks.html
 * for further important copyright and trademark information and to
 * http://www.javasoft.com/licensing.html for further important
 * licensing information for the Java (tm) Technology.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
 * CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
 * PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
 * NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
 * SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
 * SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
 * PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES").  SUN
 * SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
 * HIGH RISK ACTIVITIES.
 */
package com.inzyme.sort;

import com.inzyme.text.CollationKeyCache;

/**
 * A quick sort demonstration algorithm
 * SortAlgorithm.java
 *
 * @author James Gosling
 * @author Kevin A. Smith
 * @version 	@(#)QSortAlgorithm.java	1.3, 29 Feb 1996
 */
public class QuickSort {
  private CollationKeyCache myCache;
   
  public QuickSort(CollationKeyCache _cache) {
    myCache = _cache;
  }

  /** This is a generic version of C.A.R Hoare's Quick Sort 
   * algorithm.  This will handle arrays that are already
   * sorted, and arrays with duplicate keys.<BR>
   *
   * If you think of a one dimensional array as going from
   * the lowest index on the left to the highest index on the right
   * then the parameters to this function are lowest index or
   * left and highest index or right.  The first time you call
   * this function it will be with the parameters 0, a.length - 1.
   *
   * @param a       an integer array
   * @param lo0     left boundary of array partition
   * @param hi0     right boundary of array partition
   */
  protected void quicksort(Object[] a, int lo0, int hi0) {
    int lo = lo0;
    int hi = hi0;
    Object mid;

    // pause for redraw
    if (hi0 > lo0) {

      /* Arbitrarily establishing partition element as the midpoint of
       * the array.
       */
      mid = a[ ( lo0 + hi0 ) / 2 ];

      // loop through the array until indices cross
      while (lo <= hi) {
        /* find the first element that is greater than or equal to 
         * the partition element starting from the left Index.
         */
        while (( lo < hi0 ) && ( isLessThan(a[lo], mid) ))
          ++lo;

        /* find an element that is smaller than or equal to 
         * the partition element starting from the right Index.
         */
        while (( hi > lo0 ) && ( isGreaterThan(a[hi], mid) ))
          --hi;

        // if the indexes have not crossed, swap
        if (lo <= hi) {
          swap(a, lo, hi);

          ++lo;
          --hi;
        }
      }

      /* If the right index has not reached the left side of array
       * must now sort the left partition.
       */
      if (lo0 < hi)
        quicksort( a, lo0, hi );

      /* If the left index has not reached the right side of array
       * must now sort the right partition.
       */
      if (lo < hi0)
        quicksort( a, lo, hi0 );

    }
  }

  private void swap(Object[] a, int i, int j) {
    Object t;
    t = a[i]; 
    a[i] = a[j];
    a[j] = t;
  }

  protected boolean isLessThan(Object _a, Object _b) {
    Comparable a = getCollationKey(_a);
    Comparable b = getCollationKey(_b);
    return (a.compareTo(b) < 0);
  }

  protected boolean isGreaterThan(Object _a, Object _b) {
    Comparable a = getCollationKey(_a);
    Comparable b = getCollationKey(_b);
    return (a.compareTo(b) > 0);
  }

  protected Comparable getCollationKey(Object _a) {
    String sortValue;
    if (_a instanceof String) {
      sortValue = (String)_a;
    }
    else {
      sortValue = _a.toString();
    }
    return myCache.getCollationKey(sortValue);
  }

  public void sort(Object[] a) {
    quicksort(a, 0, a.length - 1);
  }
}
