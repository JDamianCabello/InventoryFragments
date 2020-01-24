package es.jdamiancabello.inventory.data.repository;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.jdamiancabello.inventory.data.DependencyDAO;
import es.jdamiancabello.inventory.data.InventoryDatabase;
import es.jdamiancabello.inventory.data.model.Dependency;

/**
 * Clase singleton que contiene los datos de Dependencies
 */
public class DependencyRepository {
    private static DependencyRepository repository;
    private DependencyDAO dependencyDao;


    static {
        repository = new DependencyRepository();
    }

    public static DependencyRepository getInstance() {
        return repository;
    }

    private DependencyRepository() {
        dependencyDao = InventoryDatabase.getDatabase().dependencyDao();
    }


    public List<Dependency> getAll() {
        List<Dependency> dependencies = null;
        try {
            dependencies = InventoryDatabase.databaseWriteExecutor.submit(() -> dependencyDao.getAll()).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

//        this.dependencyRepositoryListener = dependencyRepositoryListener;
//        try {
//            return new ListAsyncTask().execute().get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
        return dependencies;
    }

    public long addDependency(Dependency dependency) {
//            return new InsertAsyncTask().execute().get();
        long rowId = -1;
        try {
            rowId = InventoryDatabase.databaseWriteExecutor.submit(() -> dependencyDao.insert(dependency)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return rowId;
    }

    public boolean editDependency(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> dependencyDao.update(dependency));
        return true;
    }

    public boolean deleteDependency(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> dependencyDao.delete(dependency));
        return true;
    }

    private class ListAsyncTask extends AsyncTask<Void, Void, List<Dependency>> {

        @Override
        protected List<Dependency> doInBackground(Void... voids) {
            return dependencyDao.getAll();
        }
    }

    private class InsertAsyncTask extends AsyncTask<Dependency, Void, Long> {
        @Override
        protected Long doInBackground(Dependency... dependencies) {
            Long result = dependencyDao.insert(dependencies[0]);
            if (result == -1) {
                dependencyDao.update(dependencies[0]);
            }
            return null;
        }
    }

}
