package com.alimin.crawler.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String dateInString = "2018-03-19T19:39:58+03:00";

        Date date = formatter.parse(dateInString);
        //long d = 1516309380000L;
        Date date2 = new Date();
        date2.setTime(date.getTime());

        System.out.println(date2.getTime());
        System.out.println(formatter.format(date2));

    }
    }
