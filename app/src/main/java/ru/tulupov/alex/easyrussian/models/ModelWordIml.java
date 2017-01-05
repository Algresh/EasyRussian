package ru.tulupov.alex.easyrussian.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.tulupov.alex.easyrussian.MyApplication;
import ru.tulupov.alex.easyrussian.models.api.WordAPI;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static ru.tulupov.alex.easyrussian.Constants.YANDEX_TRANSLATE_KEY;
import static ru.tulupov.alex.easyrussian.Constants.YANDEX_TRANSLATE_URL;

public class ModelWordIml {

    public Observable<Word> addWord(Map<String, String> map) {
        return Observable.just(map).map(getMapAddWord());
    }

    public Observable<RealmResults<Word>> getWords(int page) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Word.class).findAllAsync().asObservable();
    }

    public Observable<Response<Object>> translateWord (String text, String lang) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(YANDEX_TRANSLATE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        WordAPI api = builder.build().create(WordAPI.class);
        Map<String, String> map = new HashMap<>();
        map.put("key", YANDEX_TRANSLATE_KEY);
        map.put("text", text);
        map.put("lang", lang);
        return  api.translateWord(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

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
