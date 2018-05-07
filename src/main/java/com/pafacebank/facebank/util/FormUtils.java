package com.pafacebank.facebank.util;

public class FormUtils {
    public static void sign(Form form) {
        if (form.getSignature() != null) {
            throw new RuntimeException("签名前签名需要为空");
        }
        form.setSignature(String.valueOf(form.hashCode()));
    }

    public static boolean verify(Form form) {
        if (form.getSignature() == null) {
            throw new RuntimeException("验签前签名不能为空");
        }
        String signature = form.getSignature();
        form.setSignature(null);
        return signature.equals(String.valueOf(form.hashCode()));
    }
}
