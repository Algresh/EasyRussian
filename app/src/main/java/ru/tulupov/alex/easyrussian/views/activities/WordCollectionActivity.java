package ru.tulupov.alex.easyrussian.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.tulupov.alex.easyrussian.R;
import ru.tulupov.alex.easyrussian.models.Word;
import ru.tulupov.alex.easyrussian.presenters.WordCollectionPresenter;
import ru.tulupov.alex.easyrussian.views.adapters.WordCollectionAdapter;
import ru.tulupov.alex.easyrussian.views.fragments.DialogAddWord;

public class WordCollectionActivity extends AppCompatActivity implements WordCollectionView,
        DialogAddWord.AddWordClickDialog, DialogAddWord.TranslateWordClickDialog {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private WordCollectionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarWordCollection);
        setSupportActionBar(toolbar);

        presenter = new WordCollectionPresenter();
        presenter.onCreate(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DialogAddWord dialog = new DialogAddWord();
                dialog.show(manager, "manager");
            }
        });
        presenter.showWords(0);
    }



    protected void initRecyclerView (List<Word> listWords) {
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewCollectionsWords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        WordCollectionAdapter adapter = new WordCollectionAdapter(listWords, this);
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

    @Override
    public void showNewWord(Word word) {
//        Toast.makeText(this, word.getEnglishWord() + word.getRussianWord(), Toast.LENGTH_SHORT).show();
        WordCollectionAdapter adapter = (WordCollectionAdapter) recyclerView.getAdapter();
        adapter.addWord(word);
    }

    @Override
    public void showAllWord(List<Word> listWords) {
        initRecyclerView(listWords);
    }

    @Override
    public void errorNewWord(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorAllWord(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickAddWordDialog(String russian, String english) {
        presenter.addWord(russian, english);
    }

    @Override
    public void onClickTranslateWordDialog(String word) {

    }

}
