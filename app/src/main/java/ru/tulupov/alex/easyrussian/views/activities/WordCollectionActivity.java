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

public class WordCollectionActivity extends BaseActivity implements WordCollectionView,
        DialogAddWord.AddWordClickDialog, DialogAddWord.TranslateWordClickDialog {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private WordCollectionPresenter presenter;
    private DialogAddWord dialog;

    private static final String TAG_WORD_DIALOG = "wordDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_collection);

        String title = getResources().getString(R.string.toolbarDictionary);
        initToolbar(title, R.id.toolbarWordCollection);

        presenter = new WordCollectionPresenter();
        presenter.onCreate(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                dialog = new DialogAddWord();
                dialog.show(manager, TAG_WORD_DIALOG);
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
    public void onClickTranslateWordDialog(String word, String lang) {
        presenter.translateWord(word, lang);
    }

    @Override
    public void errorTranslateWord(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTranslateWord(String word, String lang) {
//        DialogAddWord dialog = (DialogAddWord) getSupportFragmentManager().findFragmentByTag(TAG_WORD_DIALOG);
        dialog.showTranslatedWord(word, lang);
    }
}
