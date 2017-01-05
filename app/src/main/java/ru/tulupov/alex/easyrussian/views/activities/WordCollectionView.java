package ru.tulupov.alex.easyrussian.views.activities;

import java.util.List;

import ru.tulupov.alex.easyrussian.models.Word;

public interface WordCollectionView {

    void showAllWord(List<Word> listWords);
    void errorAllWord(String msg);
    void showNewWord(Word word);
    void errorNewWord(String msg);
}
