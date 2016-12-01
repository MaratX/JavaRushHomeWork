package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

/**
 * Created by HMF on 01.12.2016.
 */
public class UsersView implements View
{
    private Controller controller;

    @Override
    public void refresh(ModelData modelData)
    {

            System.out.println("All users:");

        //������ � ������� ���� ������, ������� ���� � modelData
        //����� ������ ������ ������ ������ � ���� ���������
        for (int i = 0; i < modelData.getUsers().size(); i++) {
            System.out.println("\t" + modelData.getUsers().get(i));
        }
        //� ����� ������ ���������� ����������� ������
        System.out.println("===================================================");
    }

    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

}
