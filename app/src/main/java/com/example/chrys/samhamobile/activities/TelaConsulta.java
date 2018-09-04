package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.fragments.HorariosFragment;
import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.manager.ManagerAula;
import com.example.chrys.samhamobile.transitions.ZoomOutPageTransformer;

import java.util.List;

public class TelaConsulta extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private List<Aula> listaAulas;
    private ManagerAula managerAula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);
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
        toolbar.setTitle(i.getExtras().getString("turma"));
        List<Aula> aulas = (List<Aula>) i.getSerializableExtra("aulas");
        setListaAulas(aulas);
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

    public List<Aula> getListaAulas() {
        return listaAulas;
    }

    public void setListaAulas(List<Aula> listaAulas) {
        this.listaAulas = listaAulas;
    }
}
