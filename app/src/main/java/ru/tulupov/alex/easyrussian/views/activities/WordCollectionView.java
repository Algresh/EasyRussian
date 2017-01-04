package ru.tulupov.alex.easyrussian.views.activities;

import ru.tulupov.alex.easyrussian.models.Word;

public interface WordCollectionView {

    void showNewWord(Word word);
    void errorNewWord(String msg);
}
