package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

/**
 * Created by HMF on 28.11.2016.
 */
public class Shortener
{
    private Long lastId = 0L;
    //��� ����� ��������� ��������� �������� ������
    private StorageStrategy storageStrategy;


    //constructor
    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }


    //methods

    //����� ���������� ������������� id ��� �������� ������
    public synchronized Long getId(String string){

        //��������� ���� �� ���������� �������� � ���������,
        if (storageStrategy.containsValue(string)) {
            // ���� ���� � ������� ��� ����
            return storageStrategy.getKey(string);
        }
        //���� ���������� �������� ��� � ���������, ��:
        else {
            //��������� �������� lastId
            lastId ++;
            //�������� � ��������� ����� ���� ����-�������� (����� �������� lastId � ���������� ������)
            storageStrategy.put(lastId, string);
            //������� ����� �������� lastId
            return lastId;
        }
    }

    //����� ���������� ������ ��� ��������� �������������� ��� null, ���� ������� �������� �������������
    public synchronized String getString(Long id) {

        if (storageStrategy.containsKey(id)) {
            return storageStrategy.getValue(id);
        }
        else {
            return null;
        }
    }
}
