package es.jdamiancabello.inventory.ui.sector;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.Sector;
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
            if(SectorRepository.getInstance().getSectorList().isEmpty())
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
        new AsyncTask<Void,Void, List<Sector>>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                viewSectorListContract.showProgress();
            }

            @Override
            protected void onPostExecute(List<Sector> sectorList) {
                super.onPostExecute(sectorList);
                if(sectorList.isEmpty()){
                    viewSectorListContract.noSectors();
                }
                else{
                    viewSectorListContract.refresh((ArrayList<Sector>) sectorList);
                }
                viewSectorListContract.hideProgress();
            }

            @Override
            protected List<Sector> doInBackground(Void... voids) {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                return SectorRepository.getInstance().getSectorList();
            }
        }.execute();
    }

    @Override
    public void undo(Sector sector) {

    }

}
