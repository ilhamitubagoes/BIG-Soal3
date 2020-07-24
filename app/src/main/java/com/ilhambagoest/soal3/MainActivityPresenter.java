package com.ilhambagoest.soal3;

import com.ilhambagoest.soal3.data.entity.DataOffline;
import com.ilhambagoest.soal3.data.repository.OfflineRepository;

public class MainActivityPresenter {

    private OfflineRepository repository;
    private MainActivityView mainActivityView;

    public MainActivityPresenter(OfflineRepository repository, MainActivityView mainActivityView) {
        this.repository = repository;
        this.mainActivityView = mainActivityView;
    }

    void createData(DataOffline offline) {
        Boolean result = repository.addOfflineList(offline);
        if (result) {
            mainActivityView.showSuccess();
        } else {
            mainActivityView.showFailed();
        }
    }

}
