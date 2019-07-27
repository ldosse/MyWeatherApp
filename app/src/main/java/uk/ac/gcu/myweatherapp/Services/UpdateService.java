package uk.ac.gcu.myweatherapp.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import uk.ac.gcu.myweatherapp.DataManager;

public class UpdateService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public UpdateService() {
        super("UpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("UpdateService", "Update Service running");
        DataManager.getInstance().updateData();
    }
}
