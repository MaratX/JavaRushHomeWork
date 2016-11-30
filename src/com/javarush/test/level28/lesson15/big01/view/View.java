package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by HMF on 29.11.2016.
 */
public interface View
{
    public void update(List<Vacancy> vacancies);
    public void setController(Controller controller);

}
