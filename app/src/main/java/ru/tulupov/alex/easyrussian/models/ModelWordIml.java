package ru.tulupov.alex.easyrussian.models;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.tulupov.alex.easyrussian.MyApplication;
import rx.Observable;
import rx.functions.Func1;

public class ModelWordIml {

    public Observable<Word> addWord(Map<String, String> map) {
        return Observable.just(map).map(getMapAddWord());
    }

    public Observable<RealmResults<Word>> getWords(int page) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Word.class).findAllAsync().asObservable();
    }

    private Func1<Map<String, String>, Word> getMapAddWord() {

        return new Func1<Map<String, String>, Word>() {
            @Override
            public Word call(Map<String, String> map) {
                String russian = map.get("russian");
                String english = map.get("english");
                Realm realm = Realm.getDefaultInstance();

                realm.beginTransaction();
                int nextID = realm.where(Word.class).max("id").intValue() + 1;
                Word word = realm.createObject(Word.class, nextID);
                word.setRussianWord(russian);
                word.setEnglishWord(english);

                realm.commitTransaction();
                return word;
            }
        };
    }
}
