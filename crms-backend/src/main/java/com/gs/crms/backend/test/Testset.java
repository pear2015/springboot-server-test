package com.gs.crms.backend.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by PEAR on 2018/8/1.
 */
public class Testset {
    public static  void main(String[] args){
        Set<User> userSet=new HashSet<>();
        User user=new User();
        user.setName("2");
        user.setUserId(2);
        userSet.add(user);
        User user1=new User();
        user1.setName("1");
        user1.setUserId(1);
        userSet.add(user1);
        userSet= userSet.stream().filter(u ->u.getUserId()!=null ).sorted(Comparator.comparing(User::getName).thenComparing(User::getUserId)).collect(Collectors.toSet());
        System.out.print(userSet);
        List<User> tempList = new ArrayList<User>(userSet);
        tempList=tempList.stream().filter(u ->u.getUserId()!=null ).sorted(Comparator.comparingInt(User::getUserId)).collect(Collectors.toList());
        System.out.print(tempList);
    }
}
