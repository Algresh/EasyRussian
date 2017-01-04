package ru.tulupov.alex.easyrussian.presenters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.tulupov.alex.easyrussian.models.ModelWordIml;
import ru.tulupov.alex.easyrussian.models.Word;
import ru.tulupov.alex.easyrussian.views.activities.WordCollectionView;
import rx.Observable;
import rx.Observer;

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
}
