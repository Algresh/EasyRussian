package ru.tulupov.alex.easyrussian.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import ru.tulupov.alex.easyrussian.R;

import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.TYPE_TRAIN;
import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.EN_RU_TRAIN;
import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.RU_EN_TRAIN;
import static ru.tulupov.alex.easyrussian.views.activities.TrainActivity.IDIOM_TRAIN;

public class SelectTrainsActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_train);
        String toolbar = getResources().getString(R.string.toolbarTrain);
        initToolbar(toolbar, R.id.toolbarTrain);
        initNavigationView();

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.blockEnRuTrain);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTrainsActivity.this, TrainActivity.class);
                intent.putExtra(TYPE_TRAIN, EN_RU_TRAIN);
                startActivity(intent);
            }
        });
    }
}
