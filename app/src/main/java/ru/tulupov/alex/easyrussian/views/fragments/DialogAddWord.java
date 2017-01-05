package ru.tulupov.alex.easyrussian.views.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.tulupov.alex.easyrussian.R;

public class DialogAddWord extends DialogFragment implements View.OnClickListener {

    public interface AddWordClickDialog {
        void onClickAddWordDialog(String russian, String english);
    }

    public interface TranslateWordClickDialog {
        void onClickTranslateWordDialog(String word, String lang);
    }

    private AddWordClickDialog addWordClickDialog;
    private TranslateWordClickDialog translateWordClickDialog;

    private EditText edtRussian;
    private EditText edtEnglish;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_word, null);
        view.findViewById(R.id.btnSaveWord).setOnClickListener(this);
        view.findViewById(R.id.btnTranslateWord).setOnClickListener(this);
        edtRussian = (EditText) view.findViewById(R.id.russianEdt);
        edtEnglish = (EditText) view.findViewById(R.id.englishEdt);
        builder.setView(view);

        addWordClickDialog = (AddWordClickDialog) getActivity();
        translateWordClickDialog = (TranslateWordClickDialog) getActivity();
//        builder.setView()
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        String rus = edtRussian.getText().toString();
        String eng = edtEnglish.getText().toString();
        switch (view.getId()) {
            case R.id.btnSaveWord:
                if (!rus.isEmpty() && !eng.isEmpty()) {
                    addWordClickDialog.onClickAddWordDialog(rus, eng);
                } else {
                    String str = getResources().getString(R.string.inputBothFields);
                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnTranslateWord:
                if (!rus.isEmpty() && eng.isEmpty()) {
                    translateWordClickDialog.onClickTranslateWordDialog(rus, "ru-en");
                }

                if (rus.isEmpty() && !eng.isEmpty()) {
                    translateWordClickDialog.onClickTranslateWordDialog(eng, "en-ru");
                }

                break;
        }
    }

    public void showTranslatedWord(String word, String lang) {
        if (lang.equals("ru-en")) {
            edtEnglish.setText(word);
        }

        if (lang.equals("en-ru")) {
            edtRussian.setText(word);
        }
    }
}
