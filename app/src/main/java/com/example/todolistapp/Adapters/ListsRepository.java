package com.example.todolistapp.Adapters;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.todolistapp.Models.Lists;

import java.util.List;

class ListsRepository {
    private ListsDao mListDao ;

    private LiveData<List<Lists>> getAllLists ;

    public  ListsRepository(Application app){
        ListRoomDB db = ListRoomDB.getInstance(app);
        mListDao = db.listsDao();
        getAllLists = mListDao.getAllWords();

    }

    //Operation

    //insert
    public void insert(Lists lists){
        new InsertAsyncTask(mListDao).execute(lists);

    }
    //delete
    public void delete (Lists lists){
        new DeleteAsyncTask(mListDao).execute(lists);

    }
    //update
    public void update(Lists lists){
        new UptateAsyncTask(mListDao).execute(lists);

    }
    //getAllLists
    public  LiveData<List<Lists>> getAllLists (){
        return getAllLists ;
    }
    //deleteAllLists
    public void deleteAllLists(){
        new DeleteAllListsAsyncTask(mListDao).execute();

    }

    private static class InsertAsyncTask extends AsyncTask<Lists , Void , Void>{

        private ListsDao mListsDao ;

        public  InsertAsyncTask (ListsDao listsDao){
            mListsDao = listsDao;

        }

        @Override
        protected Void doInBackground(Lists... lists) {
            mListsDao.insert(lists[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Lists , Void , Void>{

        private ListsDao mListsDao ;

        public DeleteAsyncTask (ListsDao listsDao){
            mListsDao = listsDao;

        }

        @Override
        protected Void doInBackground(Lists... lists) {
            mListsDao.delete(lists[0]);
            return null;
        }
    }

    private static class UptateAsyncTask extends AsyncTask<Lists , Void , Void>{

        private ListsDao mListsDao ;

        public UptateAsyncTask (ListsDao listsDao){
            mListsDao = listsDao;

        }

        @Override
        protected Void doInBackground(Lists... lists) {
            mListsDao.update(lists[0]);
            return null;
        }
    }

    private static class DeleteAllListsAsyncTask extends AsyncTask<Void , Void , Void>{

        private ListsDao mListsDao ;

        public DeleteAllListsAsyncTask (ListsDao listsDao){
            mListsDao = listsDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            mListsDao.deleteAllLists();
            return null;
        }
    }
}
