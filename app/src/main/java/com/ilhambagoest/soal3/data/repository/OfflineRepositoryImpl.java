package com.ilhambagoest.soal3.data.repository;

import com.ilhambagoest.soal3.data.entity.DataOffline;

import java.util.List;

import io.realm.Realm;

public class OfflineRepositoryImpl implements OfflineRepository {

    @Override
    public List<DataOffline> getOfflineList() {
        Realm realm = Realm.getDefaultInstance();
        List<DataOffline> offlineList = realm.copyFromRealm(
                realm.where(DataOffline.class).findAll());
        realm.close();
        return offlineList;
    }

    @Override
    public Boolean addOfflineList(DataOffline offline) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(offline));
        realm.close();
        return true;
    }

    @Override
    public void deleteOfflineList() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.deleteAll());
        realm.close();
    }

    @Override
    public void deleteOfflineList(String id) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.where(DataOffline.class).equalTo("id", id).findAll().deleteAllFromRealm());
        realm.close();
    }
}
