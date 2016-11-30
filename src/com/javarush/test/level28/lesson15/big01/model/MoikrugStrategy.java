package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> list = new ArrayList<>();
        int i = 1;
        try
        {
            Document doc;
            while (true)
            {
                doc = getDocument(searchString, i++);
                if (doc == null) break;
                Elements elements = doc.select("[class=job]");
                if (elements.isEmpty()) break;
                for (Element e : elements)
                {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(e.select("[class=title]").text());
                    vacancy.setCity(e.select("[class=location]").text());
                    vacancy.setCompanyName(e.select("[class=company_name]").select("a").text());
                    vacancy.setSiteName("http://moikrug.ru");
                    vacancy.setUrl("http://moikrug.ru" + e.select("[class=title").select("a").attr("href"));
                    if (e.select("[class=count]").text().equals(""))
                    {
                        vacancy.setSalary("");
                    } else
                    {
                        vacancy.setSalary(e.select("[class=count]").text());
                    }
                    list.add(vacancy);
                }
            }
        }
        catch (IOException e)
        {
        }
        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        Document doc = Jsoup.connect(String.format(URL_FORMAT, page, searchString)).userAgent("(Macintosh; Intel Mac OS X 10_10_3)").referrer("").get();
        return doc;
    }
}