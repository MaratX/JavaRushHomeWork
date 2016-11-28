package com.javarush.test.level33.lesson15.big01;



import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HMF on 28.11.2016.
 */
public class Solution
{

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> resultSet = new HashSet<>();

        for (String s : strings) {
            resultSet.add(shortener.getId(s));
        }
        return resultSet;
    }

    //����� ����� ���������� ��������� �����, ������� ������������� ����������� ��������� ���������������.
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> resultSet = new HashSet<>();

        for (Long l : keys) {
            resultSet.add(shortener.getString(l));
        }
        return resultSet;
    }

    //����� ����� ����������� ������ ���������� ��������� �� ������������ ����������
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {

        //�������� ��� ������ ���������. ��� �� ������ �������� ��� ������
        Helper.printMessage(strategy.getClass().getSimpleName());

        //������������ �������� ��������� �����, ��������� Helper � �������� ���������� ��������� elementsNumber
        Set<String> testSetStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetStrings.add(Helper.generateRandomString());
        }

        //��������� ������ ���� Shortener, ��������� ���������� ���������
        Shortener shortener = new Shortener(strategy);


        //�������� � �������� ����� ����������� ��� ��������� ������ getIds ��� �������� ��������� � ��������� ��������� ���������
        //����� ������� � �������������. ��� ������ ������� ������ ������ ����� ���������� ������������� ���������� �� ������ ������,
        // ��������, ������� �������� �� ��� �����, ������� �������� � ����� ������� ��������� ������� (����).
        // ����� ������� ��������� � �������������� �������� ���� Date.

        Set<Long> idsSet;

        Date startTime1 = new Date();
        idsSet = getIds(shortener, testSetStrings);
        Date finishTime1 = new Date();

        long msDelay1 = finishTime1.getTime() - startTime1.getTime();
        Helper.printMessage(Long.toString(msDelay1));


        //�������� � �������� ����� ����������� ��� ��������� ������ getStrings ��� �������� ��������� �
        //����������� � ���������� ������ ��������� ���������������.
        Set<String> stringSet;
        Date startTime2 = new Date();
        stringSet = getStrings(shortener, idsSet);
        Date finishTime2 = new Date();

        long msDelay2 = finishTime2.getTime() - startTime2.getTime();
        Helper.printMessage(Long.toString(msDelay2));


        //���������� ���������� �� ���������� ��������� �����, ������� ���� �������������
        // � ���������, ������� ���� ���������� ������� getStrings.
        // ���� ��������� ���������, �� ������ "���� �������.", ����� "���� �� �������.".
        if (testSetStrings.equals(stringSet)) {
            Helper.printMessage("���� �������.");
        } else {
            Helper.printMessage("���� �� �������.");
        }
    }

    public static void main(String[] args) {
        //������ ����� main(). ������ ������ ����������� ��������� HashMapStorageStrategy � ������� 10000 ���������.

        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);

    }

}
