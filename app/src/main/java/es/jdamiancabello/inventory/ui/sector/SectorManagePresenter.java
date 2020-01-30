package es.jdamiancabello.inventory.ui.sector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;
import es.jdamiancabello.inventory.data.repository.SectorRepository;

public class SectorManagePresenter implements SectorManageContract.Presenter  {
    SectorManageContract.View view;

    public SectorManagePresenter(SectorManageContract.View view){
        this.view = view;
    }


    @Override
    public void onViewCreated() {
        view.setupContentList();
    }

    @Override
    public void onAddSector(Sector sector) {
        if(validateSector(sector) ){
            if(SectorRepository.getInstance().addSector(sector) == -1)
                view.showGenericError("No se pudo añadir un pijote de sectores");
            else
                view.onSucess();
        }
    }

    private boolean validateSector(Sector sector) {
        if(validateEmptyName(sector.getName()) &
                validateEmptyShortName(sector.getShortName()) &
                validateEmptyDescription(sector.getSectorDescription()) &
                validateTooShortName(sector.getName()) &
                validateContainsEspecialChar(sector.getShortName())){
            return true;
        }
        return false;
    }

    @Override
    public void onModifySector(Sector sector) {
        if(validateSector(sector)) {
            if (SectorRepository.getInstance().editSector(sector))
                view.onSucess();
            else
                view.showGenericError("No se ha podido modificar");
        }
    }


    public boolean validateEmptyName(String s){
        if(s.isEmpty()) {
            view.onNameEmpty("El nombre está vacío");
            return false;
        }
        view.onClearErrorNameEmpty();
        return true;
    }

    public boolean validateTooShortName(String s){
        if(s.length() < 3){
            view.onShortNameShort("El nombre corto debe tener al menos 3 carácteres");
            return false;
        }
        view.onClearErrorShortNameShort();
        return true;
    }

    public boolean validateEmptyShortName(String s){
        if(s.isEmpty()) {
            view.onShortNameEmpty("Nombre corto está vacío");
            return false;
        }
        view.onClearErrorShortNameEmpty();
        return true;
    }

    public boolean validateEmptyDescription(String s){
        if(s.isEmpty()) {
            view.onDescriptionEmpty("La descripción está vacía");
            return false;
        }
        view.onClearErrorDescriptionEmpty();
        return true;
    }


    private boolean validateContainsEspecialChar(String s){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        boolean b = m.find();

        if (b){
            view.onContainsEspecialChar("No se permiten carácteres especiales");
            return false;
        }
        view.onClearErrorContainsEspecialChar();
        return true;
    }

    @Override
    public List<String> getDependecysToString() {
        List<Dependency> list = DependencyRepository.getInstance().getAll();

        List<String> listOfNames = new ArrayList<>();

        for (Dependency d: list) {
            listOfNames.add(d.getName());
        }
        return listOfNames;
    }
}
