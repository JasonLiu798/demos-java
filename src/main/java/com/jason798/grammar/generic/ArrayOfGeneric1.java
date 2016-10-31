package com.jason798.grammar.generic;


class Generic<T> {
}

/**
 * reference:
 * http://developer.51cto.com/art/201202/317813.htm
 */
public class ArrayOfGeneric1 {
    public static void main(String[] args) {
        Generic<Integer>[] genArr;
        /*
         * will throw ClassCastException :The problem is that arrays keep track of their actual type, and that type is
         * established at the point of creation of the array. So even though genArr has been cast to a Generic < Integer
         * >[] , that information only exists at compile time (and without the @SuppressWarnings annotation, you’d get a
         * warning for that cast). At run time, it’s still an array of Object, and that causes problems.
         */
        // genArr = (Generic<Integer>[]) new Object[] {};
        /* can not create a generic of array */
        // genArr=new Generic<Integer>[2];
        genArr = (Generic<Integer>[]) new Generic[2];
        System.out.println(genArr);
    }
}
