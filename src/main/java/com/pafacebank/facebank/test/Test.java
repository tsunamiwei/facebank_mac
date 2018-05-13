package com.pafacebank.facebank.test;

import com.pafacebank.facebank.test.datadict.BusinessTypeEnum;

import java.util.Arrays;

public class Test {

    void test() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println(BusinessTypeEnum.AcctClose.getName());
        System.out.println(BusinessTypeEnum.AcctClose.getClass());
        System.out.println(BusinessTypeEnum.AcctClose.getDeclaringClass());
        System.out.println(Arrays.asList(BusinessTypeEnum.AcctClose.getClass().getEnumConstants()));
        System.out.println(Arrays.asList(BusinessTypeEnum.AcctClose.getDeclaringClass().getEnumConstants()));
//        System.out.println(BusinessTypeEnum.AcctClose.getName());
    }
}
