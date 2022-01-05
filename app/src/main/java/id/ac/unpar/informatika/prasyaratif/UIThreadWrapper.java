package id.ac.unpar.informatika.prasyaratif;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.List;

import id.ac.unpar.informatika.prasyaratif.model.MataKuliah;

public class UIThreadWrapper extends Handler {
    protected final static int MSG_DISPLAY_MATAKULIAH = 0;
    protected PrasyaratContract.UI ui;

    public UIThreadWrapper(@NonNull Looper looper, PrasyaratContract.UI ui) {
        super(looper);
        this.ui = ui;
    }

    @Override
    public void handleMessage(Message msg){
        if (msg.what == MSG_DISPLAY_MATAKULIAH){
            ui.displayMataKuliah((List<List<MataKuliah>>) msg.obj);
        }
    }

    public void displayMataKuliahList(List<List<MataKuliah>> dataMataKuliahPerSemester){
        Message msg = new Message();
        msg.what = MSG_DISPLAY_MATAKULIAH;
        msg.obj = dataMataKuliahPerSemester;
        this.sendMessage(msg);
    }
}
