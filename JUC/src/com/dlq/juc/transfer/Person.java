package com.dlq.juc.transfer;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2020-12-12 18:10
 */
@NoArgsConstructor
@Setter
@Getter
public class Person {

    private Integer id;
    private String personName;

    public Person(String personName){
        this.personName = personName;
    }
}
