package com.ilhambagoest.soal3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ilhambagoest.soal3.adapter.OfflineListAdapter;
import com.ilhambagoest.soal3.data.entity.DataOffline;
import com.ilhambagoest.soal3.data.repository.OfflineRepository;
import com.ilhambagoest.soal3.data.repository.OfflineRepositoryImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainActivityView, OfflineListAdapter.OnItemSelected {

    MainActivityPresenter presenter;
    OfflineListAdapter adapter;
    OfflineRepository repository;
    RecyclerView rvList;
    List<DataOffline> offlinesSorted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Realm.init(this);

        repository = new OfflineRepositoryImpl();
        presenter = new MainActivityPresenter(repository, this);

        rvList = findViewById(R.id.rv_list);
        showRecyclerList();

        String[] namaList = {"Andi","Budi","Bayu","Tono","Jonah","Sugiono","Supriyadi","Rini","Bella", "Indah"};
        String[] jabatan = {"Dokter","Suster","Perawat","Bidan","Satpam","Driver"};
        String[] keperluan = {"Merawat", "Memeriksa", "Menjaga", "Melahirkan", "Mengamankan", "Menyupir"};

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Random r = new Random();
            DataOffline offline = new DataOffline();
            offline.setId(String.valueOf(repository.getOfflineList().size()+1));
            offline.setNama(namaList[r.nextInt(namaList.length)]);
            offline.setJabatan(jabatan[r.nextInt(jabatan.length)]);
            offline.setKeperluan(keperluan[r.nextInt(keperluan.length)]);

            Toast.makeText(this, offline.getId() + " " + offline.getNama() + " " + offline.getJabatan() + " " + offline.getKeperluan(), Toast.LENGTH_SHORT).show();
            repository.addOfflineList(offline);

            updateDataOffline();
            adapter.updateOfflines(offlinesSorted);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "Sukses Menambahkan Data Offline", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailed() {
        Toast.makeText(this, "Gagal Menambahkan Data Offline", Toast.LENGTH_SHORT).show();
    }

    private void showRecyclerList() {
        rvList.setLayoutManager(new LinearLayoutManager(this));

        updateDataOffline();

        adapter = new OfflineListAdapter(this, this);
        adapter.updateOfflines(offlinesSorted);
        adapter.notifyDataSetChanged();
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setAdapter(adapter);
    }

    @Override
    public void onSelected(DataOffline offline) {
        Toast.makeText(this, offline.getId() + " " + offline.getNama() + " " + offline.getJabatan() + " " + offline.getKeperluan(), Toast.LENGTH_SHORT).show();
    }

    private void updateDataOffline(){
        offlinesSorted = new ArrayList<>();
        offlinesSorted.clear();
        offlinesSorted.addAll(repository.getOfflineList());
        Collections.sort(offlinesSorted, (o1, o2) -> o1.getId().compareTo(o2.getId()));
    }

}