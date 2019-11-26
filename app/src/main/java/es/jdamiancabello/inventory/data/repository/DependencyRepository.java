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

    public static DependencyRepository getInstance(){
        if(repository==null)
            repository = new DependencyRepository();
        return repository;
    }

    private DependencyRepository(){
        dependencyList = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        for (int i = 1; i <= 10; i++) {
            dependencyList.add(new Dependency("dependency_" + i, "dp_" + i, "This re the dependency number: " + i, "No image"));
        }
    }

    public boolean addDependency(Dependency dependency){
        return dependencyList.add(dependency);
    }

    public List<Dependency> getDependencyList(){
        return dependencyList;
    }
}
