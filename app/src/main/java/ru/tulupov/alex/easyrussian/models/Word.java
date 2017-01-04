package ru.tulupov.alex.easyrussian.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Word extends RealmObject {

    @PrimaryKey
    private int id;
    private String russianWord;
    private String englishWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRussianWord() {
        return russianWord;
    }

    public void setRussianWord(String russianWord) {
        this.russianWord = russianWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }
}
