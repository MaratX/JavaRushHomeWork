package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by HMF on 01.12.2016.
 */
public class MainModel implements Model
{
    private ModelData modelData = new ModelData();
    //������ ���������� � ��������, ������ ���� UserService userService, ������������� ��������
    private UserService userService = new UserServiceImpl();



    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers()
    {

        //������� ���� ������������� ����� 1 � 100 ��������
        //������ ���� ������������� � modelData
        modelData.setUsers(getActiveUsers(userService.getUsersBetweenLevels(1, 100)));
    }


    private List<User> getActiveUsers(List<User> userList){
        return userService.filterOnlyActiveUsers(userList);
    }
}
