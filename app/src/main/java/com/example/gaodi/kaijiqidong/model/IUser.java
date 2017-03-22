package com.example.gaodi.kaijiqidong.model;

/**
 * Created by gaodi on 2017/3/17.
 */

public interface IUser {
    String getName();

    String getPasswd();

    int checkUserValidity(String name, String passwd);
}
