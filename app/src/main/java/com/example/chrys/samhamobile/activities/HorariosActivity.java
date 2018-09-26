package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.fragments.HorariosFragment;
import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.manager.Constantes;
import com.example.chrys.samhamobile.manager.ManagerAula;
import com.example.chrys.samhamobile.transitions.ZoomOutPageTransformer;

import java.util.List;

public class HorariosActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private List<Aula> listaAulas;
    private ManagerAula managerAula;
    private int turno;
    private int user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        managerAula = new ManagerAula();
        getViews();

        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        obterAulas();
        configurarViewPagerAdapter();
    }

    public void obterAulas(){

        Intent i = getIntent();
        List<Aula> aulas = (List<Aula>) i.getSerializableExtra("aulas");
        setListaAulas(aulas);
        user = i.getExtras().getInt("user");

        if(user == Constantes.ALUNO){
            String turma = i.getExtras().getString("turma");
            setTurno(turma.charAt(0));
            toolbar.setTitle(turma);
        }else{
            String professor = i.getExtras().getString("professor");
            toolbar.setTitle(professor);
        }


    }

    public void getViews(){
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar_consulta);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setActionBar(toolbar);
    }

    private void setActionBar(Toolbar toolbar) {

    }

    public void configurarViewPagerAdapter(){
        String[] array = getResources().getStringArray(R.array.turnos);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setArray(array);
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
            return HorariosFragment.newInstance(position, listaAulasTurno, user);
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
    }
}
