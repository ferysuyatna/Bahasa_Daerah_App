package com.bahasadaerahapp.model;

import com.bahasadaerahapp.R;

import java.util.ArrayList;

public class DictionaryData { // array to store data that will be displayed on RecyclerView
        public static String[] dataIndo = new String[] {
                "Aku",
                "Kamu"
        };

    public static String[] dataDaerah = new String[] {
            "Abdi, Aing, Urang",
            "Maneh, Sia"
    };


    public static ArrayList<Dictionary> getListData() {
        ArrayList<Dictionary> list = new ArrayList<>();
        for (int position = 0; position < dataIndo.length; position++) {
            Dictionary language = new Dictionary();
            language.setBhsIndo(dataIndo[position]);
            language.setBhsDaerah(dataDaerah[position]);
            list.add(language);
        }
        return list;
    }

}
