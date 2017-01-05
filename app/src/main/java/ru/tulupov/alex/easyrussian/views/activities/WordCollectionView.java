package ru.tulupov.alex.easyrussian.views.activities;

import java.util.List;

import ru.tulupov.alex.easyrussian.models.Word;

public interface WordCollectionView {

    void showTranslateWord(String word, String lang);
    void errorTranslateWord(String msg);
    void showAllWord(List<Word> listWords);
    void errorAllWord(String msg);
    void showNewWord(Word word);
    void errorNewWord(String msg);
}
