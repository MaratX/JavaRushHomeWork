package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

/**
 * Created by HMF on 28.11.2016.
 */
public class Shortener
{
    private Long lastId = 0L;
    //тут будет хранитьс€ стратеги€ хранени€ данных
    private StorageStrategy storageStrategy;


    //constructor
    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }


    //methods

    //будет возвращать идентификатор id дл€ заданной строки
    public synchronized Long getId(String string){

        //ѕроверить есть ли переданное значение в хранилище,
        if (storageStrategy.containsValue(string)) {
            // если есть Ц вернуть его ключ
            return storageStrategy.getKey(string);
        }
        //≈сли преданного значени€ нет в хранилище, то:
        else {
            //”величить значение lastId
            lastId ++;
            //ƒобавить в хранилище новую пару ключ-значение (новое значение lastId и переданную строку)
            storageStrategy.put(lastId, string);
            //¬ернуть новое значение lastId
            return lastId;
        }
    }

    //будет возвращать строку дл€ заданного идентификатора или null, если передан неверный идентификатор
    public synchronized String getString(Long id) {

        if (storageStrategy.containsKey(id)) {
            return storageStrategy.getValue(id);
        }
        else {
            return null;
        }
    }
}
