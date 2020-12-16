package com.example.todolistapp.Adapters;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolistapp.Models.Lists;
import com.example.todolistapp.Models.Tasks;

import java.util.List;

class TaskRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Tasks>> getAllTasks;

    public TaskRepository(Application app) {
        ListRoomDB db = ListRoomDB.getInstance(app);
        mTaskDao = db.taskDao();
      //  getAllTasks = mTaskDao.getAlltasks();
    }

    //Operation

    public  LiveData<List<Tasks>> getAllTask(int categoryId){
        return mTaskDao.getAlltasks(categoryId);
    }
    public  LiveData<List<Tasks>> getSearchTask(int categoryId,String filter){
        return mTaskDao.getOpenTask(categoryId,filter);
    }


    //insert
    public void insert(Tasks tasks) {
     new InsertAsyncTask(mTaskDao).execute(tasks);

    }

    //delete
    public void delete(Tasks tasks) {
        new DeleteAsyncTask(mTaskDao).execute(tasks);

    }

    //update
    public void update(Tasks tasks) {
        new UpdateAsyncTask(mTaskDao).execute(tasks);

    }

    //getAllTasks
    public LiveData<List<Tasks>> getAllTask() {

     return  getAllTasks ;
    }

    //deleteAllLists
    public void deleteAllTask() {
        new DeleteAllTaskAsyncTask(mTaskDao).execute();

    }

    private static class InsertAsyncTask extends AsyncTask<Tasks, Void, Void> {

        private TaskDao mtaskDao;

        public InsertAsyncTask(TaskDao taskDao) {
            mtaskDao = taskDao;

        }
        @Override
        protected Void doInBackground(Tasks... tasks) {
            mtaskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Tasks, Void, Void> {

        private TaskDao mtaskDao;

        public DeleteAsyncTask(TaskDao taskDao) {
            mtaskDao = taskDao;

        }
        @Override
        protected Void doInBackground(Tasks... tasks) {
            mtaskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Tasks, Void, Void> {

        private TaskDao mtaskDao;

        public UpdateAsyncTask(TaskDao taskDao) {
            mtaskDao = taskDao;

        }
        @Override
        protected Void doInBackground(Tasks... tasks) {
            mtaskDao.update(tasks[0]);
            return null;
        }
    }
    private static class DeleteAllTaskAsyncTask extends AsyncTask<Void, Void, Void> {

        private TaskDao mtaskDao;

        public DeleteAllTaskAsyncTask(TaskDao taskDao) {
            mtaskDao = taskDao;

        }


        @Override
        protected Void doInBackground(Void... voids) {
            mtaskDao.deleteAlltasks();
            return null;
        }
    }






}
