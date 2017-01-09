package ru.tulupov.alex.easyrussian.views.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.tulupov.alex.easyrussian.R;
import ru.tulupov.alex.easyrussian.views.fragments.TrainFragment;

public class TrainActivity extends BaseActivity {

    public static final String TYPE_TRAIN = "typeTrain";
    public static final String RU_EN_TRAIN = "ruEnTrain";
    public static final String EN_RU_TRAIN = "enRuTrain";
    public static final String IDIOM_TRAIN = "idiomTrain";

    private String currentTypeTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        Intent intent = getIntent();
        String typeTrain = intent.getStringExtra(TYPE_TRAIN);

        String toolbar = "";
        if (typeTrain.equals(RU_EN_TRAIN)) {
            toolbar = getResources().getString(R.string.train_label_RuEn);
            currentTypeTrain = RU_EN_TRAIN;
        }

        if (typeTrain.equals(EN_RU_TRAIN)) {
            toolbar = getResources().getString(R.string.train_label_RuEn);
            currentTypeTrain = EN_RU_TRAIN;
        }

        if (typeTrain.equals(IDIOM_TRAIN)) {
            toolbar = getResources().getString(R.string.train_label_RuEn);
            currentTypeTrain = IDIOM_TRAIN;
        }

        initToolbar(toolbar, R.id.toolbarTrain);
        initFragment();

    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        TrainFragment fragmentTrain = new TrainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE_TRAIN, currentTypeTrain);
        fragmentTrain.setArguments(bundle);
        manager.beginTransaction()
                .add(R.id.train_container, fragmentTrain)
                .commit();

    }
}
