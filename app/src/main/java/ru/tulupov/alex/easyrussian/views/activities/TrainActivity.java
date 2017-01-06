package ru.tulupov.alex.easyrussian.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.tulupov.alex.easyrussian.R;

public class TrainActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        String toolbar = getResources().getString(R.string.toolbarTrain);
        initToolbar(toolbar, R.id.toolbarTrain);
        initNavigationView();
    }
}
