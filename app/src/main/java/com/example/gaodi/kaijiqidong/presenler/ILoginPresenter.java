package com.example.gaodi.kaijiqidong.presenler;

/**
 * Created by gaodi on 2017/3/17.
 */

public interface ILoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);
}
