package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HMF on 01.12.2016.
 */
public class FakeModel implements Model
{
    private ModelData modelData = new ModelData();


    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {

        //test data
        List<User> usr = new ArrayList<>();

        usr.add(new User("Ivan", 1, 1));
        usr.add(new User("Petr", 2, 3));
        usr.add(new User("Isidor", 3, 2));

        modelData.setUsers(usr);
    }
}
