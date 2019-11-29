package es.jdamiancabello.inventory.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.Dependency;

/**
 * Clase singleton que contiene los datos de Dependencies
 */
public class DependencyRepository {
    private static DependencyRepository repository;
    private List<Dependency> dependencyList;

    static {
        repository = new DependencyRepository();
    }

    public static DependencyRepository getInstance() {
        return repository;
    }

    private DependencyRepository() {
        dependencyList = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        for (int i = 1; i <= 3; i++) {
            dependencyList.add(new Dependency("dependency_" + i, "dp_" + i, "This re the dependency number: " + i, "No image"));
        }
    }

    public boolean addDependency(Dependency dependency) {
        return dependencyList.add(dependency);
    }

    public boolean modifyDependency(Dependency newDependency) {
        for (Dependency it : dependencyList) {
            if (it.getShortName().equals(newDependency.getShortName())) {
                it.setName(newDependency.getName());
                it.setDescription(newDependency.getDescription());
                return true;
            }
        }
        return false;
    }

    public boolean deleteDependency(Dependency dependency) {
        return dependencyList.remove(dependency);
    }

    public List<Dependency> getDependencyList() {
        return dependencyList;
    }

    public int getPosition(Dependency dependency) {
        int i = 0;

        for (int j = 0; j < dependencyList.size(); j++) {
            if (dependencyList.get(i).getShortName().equals(dependency.getShortName()))
                return i;


        }
        return -1;
    }

}
