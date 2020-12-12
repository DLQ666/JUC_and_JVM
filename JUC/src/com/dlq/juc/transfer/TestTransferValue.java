package com.dlq.juc.transfer;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2020-12-12 18:19
 */
public class TestTransferValue {

    public void chageValue1(int age) {
        age = 30;
    }
    public void chageValue2(Person person) {
        person.setPersonName("xxx");
    }
    public void chageValue3(String str) {
        str = "xxx";
    }
    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.chageValue1(age);
        System.out.println("age-----" + age);

        Person person = new Person("abc");
        test.chageValue2(person);
        System.out.println("personName-----" + person.getPersonName());

        String str = "abc";
        test.chageValue3(str);
        System.out.println("String------" + str);
    }
}
