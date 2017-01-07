package ru.tulupov.alex.easyrussian.models;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class ModelTrainImpl {

    public Observable<RealmResults<Word>> getTrainWords() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Word.class).findAllAsync().asObservable();
    }

    public Observable<RealmResults<Word>> getThreeOtherWord(Word word) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Word.class).notEqualTo("id", word.getId()).findAllAsync().asObservable();
    }
}
