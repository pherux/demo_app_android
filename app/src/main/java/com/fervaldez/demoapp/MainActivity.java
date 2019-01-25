package com.fervaldez.demoapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fervaldez.demoapp.fragments.ButtonsFragment;
import com.fervaldez.demoapp.fragments.GithubFragment;
import com.fervaldez.demoapp.fragments.WebFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GithubFragment.InteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToWebSite(String site) {
        WebFragment fragment = WebFragment.newInstance(site);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "frag").commit();

    }


    private void gotoButtons() {
        ButtonsFragment fragment = ButtonsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "buttons_frag").commit();


    }

    private void gotoGithub() {
        String url = "https://api.github.com/search/repositories?q=language:kotlin&sort=stars&order=desc";
        GithubFragment fragment = GithubFragment.newInstance(url);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "git_frag").commit();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_google) {
            goToWebSite("https://www.google.com/");
        } else if (id == R.id.nav_buttons) {
            gotoButtons();

        } else if (id == R.id.nav_github) {
            gotoGithub();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOpenWebSite(String url) {
        goToWebSite(url);
    }
}
