package ru.tulupov.alex.easyrussian.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import ru.tulupov.alex.easyrussian.R;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getResources().getString(R.string.toolbarMain);
        initToolbar(title, R.id.toolbarMain);
        initNavigationView();

        FrameLayout layout = (FrameLayout) findViewById(R.id.blockDictionaryMain);
        layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blockDictionaryMain:
                Intent intent = new Intent(this, WordCollectionActivity.class);
                startActivity(intent);
        }
    }
}
