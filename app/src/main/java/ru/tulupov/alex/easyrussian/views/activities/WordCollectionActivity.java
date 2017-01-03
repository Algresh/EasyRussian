package ru.tulupov.alex.easyrussian.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ru.tulupov.alex.easyrussian.R;
import ru.tulupov.alex.easyrussian.models.Word;
import ru.tulupov.alex.easyrussian.views.adapters.WordCollectionAdapter;

public class WordCollectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarWordCollection);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initRecyclerView();
    }



    protected void initRecyclerView () {
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewCollectionsWords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        WordCollectionAdapter adapter = new WordCollectionAdapter(getListWords(), this);
        recyclerView.setAdapter(adapter);
    }

    private List<Word> getListWords() {
        List<Word> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Word word = new Word();
            word.setId(i);
            word.setEnglishWord("Hello");
            word.setRussianWord("Привет");
            list.add(word);
        }

        return list;
    }

}
