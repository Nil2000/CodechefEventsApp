package com.example.codechefeventsapp.data.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.codechefeventsapp.FireStoreStorage.Storage;
import com.example.codechefeventsapp.data.dao.EventDao;
import com.example.codechefeventsapp.data.database.EventDatabase;
import com.example.codechefeventsapp.data.models.Event;

import java.util.List;

import static com.example.codechefeventsapp.activities.MainActivity.TAG;

public class EventRepository {

    private EventDao eventDao;
    private LiveData<List<Event>> allEvent;
    private Storage storage;

    public EventRepository(Application application) {
        eventDao = EventDatabase.getInstance(application).getEventDao();
        allEvent = eventDao.getAllEvents();
    }

    public void insert(Event event) {
        new InsertAsyncTask().execute(event);
    }

    public void update(Event event) {
        new UpdateAsyncTask().execute(event);
    }

    public void delete(Event event) {
        new DeleteAsyncTask().execute(event);
    }

    public LiveData<List<Event>> getAllEvent() {
        return allEvent;
    }

    private class InsertAsyncTask extends AsyncTask<Event, Void, Void> {
        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insert(events[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Event, Void, Void> {
        @Override
        protected Void doInBackground(Event... events) {
            eventDao.update(events[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Event, Void, Void> {
        @Override
        protected Void doInBackground(Event... events) {
            eventDao.delete(events[0]);
            return null;
        }
    }

    public void makeFirebaseCallAndStore() {
        Log.d(TAG, "makeFirebaseCallAndStore: ");
        // TODO: Firebase logic
    }
}