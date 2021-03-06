package com.bahasadaerahapp.kamus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.bahasadaerahapp.R;
import com.bahasadaerahapp.adapter.DictionaryLanguageList;
import com.bahasadaerahapp.adapter.DictionaryListAdapter;
import com.bahasadaerahapp.model.Dictionary;
import com.bahasadaerahapp.model.DictionaryData;
import com.bahasadaerahapp.model.DictionaryLanguage;
import com.bahasadaerahapp.model.DictionaryLanguageData;

import java.util.ArrayList;

public class KamusActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Dictionary> list;

    final String STATE_TITLE = "state_title";
    final String STATE_LIST = "state_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);

        recyclerView = findViewById(R.id.rv_listbahasa);
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
//        list.addAll(MountainData.getListData());
//        showRecyclerViewList();

        if (savedInstanceState == null) {
            setActionBarTitle("List Language");
            list.addAll(DictionaryData.getListData());
            showRecyclerViewList();
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<Dictionary> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void showRecyclerViewList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DictionaryListAdapter listAdapter = new DictionaryListAdapter(this);
        listAdapter.setListMountain(list);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
    }
}
