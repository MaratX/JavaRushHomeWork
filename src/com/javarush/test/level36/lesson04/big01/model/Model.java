package com.javarush.test.level36.lesson04.big01.model;

/**
 * Created by HMF on 01.12.2016.
 */
public interface Model
{
    ModelData getModelData();

    void loadUsers();

    void loadDeletedUsers();
}
