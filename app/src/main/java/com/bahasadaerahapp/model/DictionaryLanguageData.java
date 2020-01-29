package com.bahasadaerahapp.model;

import com.bahasadaerahapp.R;

import java.util.ArrayList;

public class DictionaryLanguageData { // array to store data that will be displayed on RecyclerView
        public static String[] data = new String[] {
                "Bahasa Sunda",
                "Bahasa Bali"
        };

    private static int[] languageImages = new int[]{
            R.drawable.bandung,
            R.drawable.bali
    };

//        public static ArrayList<DirectoryLanguage> getListData() {
//            DirectoryLanguage mountain;
//            ArrayList<DirectoryLanguage> list = new ArrayList<>();
//
//            for (String[] mData : data) {
//                mountain = new DirectoryLanguage();
//                mountain.setName(mData[0]);
//              //  mountain.setPhoto(mData[1]);
//                mountain.setPhoto(mData[0]);
//                list.add(mountain);
//            }
//
//            return list;
//        }

    public static ArrayList<DictionaryLanguage> getListData() {
        ArrayList<DictionaryLanguage> list = new ArrayList<>();
        for (int position = 0; position < data.length; position++) {
            DictionaryLanguage language = new DictionaryLanguage();
            language.setName(data[position]);
            language.setPhoto(languageImages[position]);
            list.add(language);
        }
        return list;
    }

}
