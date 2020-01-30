package es.jdamiancabello.inventory.ui.sector;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;
import es.jdamiancabello.inventory.data.repository.SectorRepository;

public class SectorPresenter implements SectorListContract.Presenter {
    public SectorListContract.View viewSectorListContract;

    public SectorPresenter(SectorListContract.View viewSectorListContract) {
        this.viewSectorListContract = viewSectorListContract;
    }

    @Override
    public void delete(Sector sector) {
        //1. Realizar la operacion en el repo y comprobar el resultado
        if(SectorRepository.getInstance().deleteSector(sector)) {
            //1.2 Comprobar si no hay datos
            if(SectorRepository.getInstance().getAll().isEmpty())
                viewSectorListContract.noSectors();
            else
            {
                //Aqui se muestra el toask de si hay datos (o la imagen)
            }
            viewSectorListContract.onSuccessDeleted();
        }
    }


    @Override
    public void load() {
        viewSectorListContract.refresh((ArrayList<Sector>) SectorRepository.getInstance().getAll());
    }

    @Override
    public void undo(Sector sector) {
        if (SectorRepository.getInstance().addSector(sector) != -1) {
            viewSectorListContract.onSucessUndo(sector);
        }
    }



}