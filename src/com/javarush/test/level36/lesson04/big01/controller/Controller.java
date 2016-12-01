package com.javarush.test.level36.lesson04.big01.controller;

import com.javarush.test.level36.lesson04.big01.model.Model;
import com.javarush.test.level36.lesson04.big01.view.UsersView;

/**
 * Created by HMF on 01.12.2016.
 */
public class Controller
{
    private Model model;
    private UsersView usersView;

    public void onShowAllUsers(){
        //обратиться к модели и инициировать загрузку юзеров
        model.loadUsers();

        usersView.refresh(model.getModelData());

    }

    public void onShowAllDeletedUsers() {
        model.loadDeletedUsers();
        usersView.refresh(model.getModelData());
    }

    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }
}
