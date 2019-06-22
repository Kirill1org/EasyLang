package com.easylang;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Dictionary.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "dictionary";
    private static AppDatabase INSTANCE;
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE).execute();
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract DictionaryDAO dictionaryDAO();

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private DictionaryDAO dictionaryDAO;

        public PopulateAsyncTask(AppDatabase instance) {
            dictionaryDAO = instance.dictionaryDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dictionaryDAO.deleteAll();
            dictionaryDAO.insert(new Dictionary("en", "ru", "cat", "кот"));
            dictionaryDAO.insert(new Dictionary("ru", "en", "кот", "cat"));
            dictionaryDAO.insert(new Dictionary("en", "ru", "car", "автомобиль"));
            dictionaryDAO.insert(new Dictionary("ru", "en", "автомобиль", "car"));
            return null;
        }
    }
}
