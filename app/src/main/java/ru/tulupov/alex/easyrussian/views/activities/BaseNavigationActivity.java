package ru.tulupov.alex.easyrussian.views.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.tulupov.alex.easyrussian.R;

public class BaseNavigationActivity extends BaseActivity {

    protected void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(selectMenuNavigationView());
    }

    private  NavigationView.OnNavigationItemSelectedListener selectMenuNavigationView() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                Intent intent;
                switch (item.getItemId()){

                    case R.id.navMenuMain:
                        intent = new Intent(BaseNavigationActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navMenuTrain:
                        intent = new Intent(BaseNavigationActivity.this, SelectTrainsActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        };
    }

    @Override
    protected void initToolbar(String title, int idToolbar) {
        toolbar = (Toolbar) findViewById(idToolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
        }
    }
}
