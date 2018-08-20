package com.gs.crms.backend.test;

import java.util.*;

/**
 * Created by PEAR on 2018/8/1.
 */
public class HashSetTest {
    public static  void main(String args[]){
        HashSet<String> names = new HashSet<String>();
        names.add("Asker");
        names.add("Crak");
        names.add("Bayliss");
        names.add("Mohna");
        names.add("Dina");
        System.out.println("HashSet before sorting : " + names);
        //将HashSet转换为List，然后使用Collections.sort()进行排序
        List<String> tempList = new ArrayList<String>(names);
        Collections.sort(tempList);
        System.out.println("HashSet element in sorted order : " + tempList);
        // 使用TreeSet对HashSet进行排序
        TreeSet<String> sorted = new TreeSet<String>(names);
        System.out.println("HashSet sorted using TreeSet : " + sorted);
        HashSet<String> setTest = new HashSet<String>();
        setTest.add("bbc");
        setTest.add("abc");
        setTest.add("acb");
        LinkedList<String> setSort = new LinkedList<String>(setTest);
        //Collections.sort(setSort);
        Comparator<String> setComp = Collections.reverseOrder();
        Collections.sort(setSort, setComp);
    }
}
