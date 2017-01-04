package ru.tulupov.alex.easyrussian.models;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import ru.tulupov.alex.easyrussian.MyApplication;
import rx.Observable;
import rx.functions.Func1;

public class ModelWordIml {

    public Observable<Word> addWord(Map<String, String> map) {
        return Observable.just(map).map(getMapAddWord());
    }

    private Func1<Map<String, String>, Word> getMapAddWord() {

        return new Func1<Map<String, String>, Word>() {
            @Override
            public Word call(Map<String, String> map) {
                String russian = map.get("russian");
                String english = map.get("english");
                Realm realm = Realm.getDefaultInstance();

                realm.beginTransaction();
                Word word = realm.createObject(Word.class);
                word.setRussianWord(russian);
                word.setEnglishWord(english);

                int nextID = ((int) realm.where(Word.class).maximumInt("id")) + 1;
                word.setId(nextID);
                realm.commitTransaction();
                return word;
            }
        };
    }
}
