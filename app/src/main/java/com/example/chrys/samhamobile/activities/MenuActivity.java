package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.fragments.HorariosFragment;
import com.example.chrys.samhamobile.manager.ManagerAula;
import com.example.chrys.samhamobile.transitions.ZoomOutPageTransformer;

import java.util.List;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Aula> listaAulas;
    private ManagerAula managerAula;
    private int turno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //obterAulasTurma();
        //managerAula = new ManagerAula();
        //configurarViewPagerAdapter();
    }

    public void settingsMenu(){

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*public void obterAulasTurma(){

        Intent i = getIntent();
        String user = i.getExtras().getString("user");

        if(user.equals("aluno")){
            String turma = i.getExtras().getString("turma");
            this.setTitle(turma);
            setTurno(turma.charAt(0));
            List<Aula> aulas = (List<Aula>) i.getSerializableExtra("aulas");
            setListaAulas(aulas);
            Toolbar toolbar = findViewById(R.id.toolbar_menu);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(view -> {
                finish();
            });
        }else{
            settingsMenu();
        }

    }

    public void configurarViewPagerAdapter(){

        String[] array = getResources().getStringArray(R.array.turnos);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setArray(array);
        ViewPager viewPager = findViewById(R.id.viewPager2);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(array.length);
        viewPager.setCurrentItem(getTurno());
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private String[] array = null;

        public ViewPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            List listaAulasTurno = managerAula.obterAulasTurno(position, listaAulas);
            return HorariosFragment.newInstance(position, listaAulasTurno);
        }

        @Override
        public int getCount() {
            return array.length;
        }

        public String[] getArray() {
            return array;
        }

        public void setArray(String[] array) {
            this.array = array;
        }
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(char t) {
        if(t == 'M')
            this.turno = 0;
        else if(t == 'V')
            this.turno = 1;
        else
            this.turno = 2;
    }

    public List<Aula> getListaAulas() {
        return listaAulas;
    }

    public void setListaAulas(List<Aula> listaAulas) {
        this.listaAulas = listaAulas;
    }*/
}
