package com.ilhambagoest.soal3.data.repository;

import com.ilhambagoest.soal3.data.entity.DataOffline;

import java.util.List;

public interface OfflineRepository{
    List<DataOffline> getOfflineList();
    Boolean addOfflineList(DataOffline offline);
    void deleteOfflineList();
    void deleteOfflineList(String id);
}
