package ru.tulupov.alex.easyrussian.views.activities;

import android.os.Bundle;

import ru.tulupov.alex.easyrussian.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getResources().getString(R.string.toolbarMain);
        initToolbar(title, R.id.toolbarMain);
        initNavigationView();
    }
}
