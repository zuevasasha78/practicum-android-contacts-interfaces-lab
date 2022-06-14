package ru.yandex.practicum.contacts.utils.android;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import ru.yandex.practicum.contacts.presentation.main.MainViewModel;

import androidx.annotation.NonNull;

public class Debouncer {

    private static final int MESSAGE_ID = 1;
    private static final int DELAY = 500;

    private final MainViewModel viewModel;

    public Debouncer(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message message) {
            if (message.what == MESSAGE_ID) {
                viewModel.search();
                return;
            }
            super.handleMessage(message);
        }
    };

    public void updateValue(String value) {
        final Message message = Message.obtain(handler, MESSAGE_ID, value);
        handler.removeMessages(MESSAGE_ID);
        handler.sendMessageDelayed(message, DELAY);
    }
}
