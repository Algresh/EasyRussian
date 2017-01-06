package ru.tulupov.alex.easyrussian.presenters;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Response;
import ru.tulupov.alex.easyrussian.models.ModelWordIml;
import ru.tulupov.alex.easyrussian.models.Word;
import ru.tulupov.alex.easyrussian.views.activities.WordCollectionView;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class WordCollectionPresenter {

    private WordCollectionView view;
    private List<Word> listWords;
    private ModelWordIml model;

    public void onCreate(WordCollectionView view) {
        this.view = view;
        model = new ModelWordIml();
        listWords = new ArrayList<>();
    }

    public void addWord(String russianWord, String englishWord) {
        Map<String, String> map = new HashMap<>();
        map.put("russian", russianWord);
        map.put("english", englishWord);

        model.addWord(map).subscribe(new Observer<Word>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.errorNewWord(e.getMessage());
            }

            @Override
            public void onNext(Word word) {
                view.showNewWord(word);
                listWords.add(word);
            }
        });
    }

    public void translateWord(final String word, String lang) {
        model.translateWord(word, lang).subscribe(new Observer<Response<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.errorTranslateWord(e.getMessage());
            }

            @Override
            public void onNext(Response<Object> response) {
                String json = response.body().toString();
                Gson gson = new GsonBuilder().create();
                Map fields = gson.fromJson(json, Map.class);
                String lang = (String) fields.get("lang");
                String text = fields.get("text").toString();
                if (text.length() > 2) {
                    view.showTranslateWord(text.substring(1, text.length() - 1), lang);
                } else {
                    view.errorTranslateWord("word empty");

                }
            }
        });
    }

    public void showWords(int page) {
        model.getWords(page).map(new Func1<RealmResults<Word>, List<Word>>() {
            @Override
            public List<Word> call(RealmResults<Word> result) {

                List<Word> listWords = new ArrayList<>();
                for(Word word : result) {
                    listWords.add(word);
                }

                return listWords;
            }
        }).subscribe(new Observer<List<Word>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.errorAllWord(e.getMessage());
            }

            @Override
            public void onNext(List<Word> words) {
                view.showAllWord(words);
            }
        });
    }
}
