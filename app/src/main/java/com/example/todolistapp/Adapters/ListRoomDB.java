package com.example.todolistapp.Adapters;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todolistapp.Models.Lists;
import com.example.todolistapp.Models.Tasks;

@Database(entities = {Lists.class, Tasks.class} , version = 2)
abstract
class ListRoomDB extends RoomDatabase {

    private static ListRoomDB instance ;
    public abstract ListsDao listsDao();
    public abstract TaskDao taskDao();


    //Singilton
    public static synchronized ListRoomDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext() ,
                    ListRoomDB.class ,"List_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDataAsyncTask(instance).execute();
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private  static  class PopulateDataAsyncTask extends AsyncTask<Void , Void , Void>{

        private ListsDao mListsDao ;
        private TaskDao mtaskDao ;
        PopulateDataAsyncTask(ListRoomDB db){
            mListsDao = db.listsDao();
            mtaskDao=db.taskDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            mListsDao.insert(new Lists("Dlia"));
            mListsDao.insert(new Lists("Personal"));
            mListsDao.insert(new Lists("University"));

            return null;
        }
    }

}
