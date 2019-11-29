package es.jdamiancabello.inventory.ui.sector;

public class SectorSpinnerContentPresenter implements SectorSpinnerContentContract.Presenter {
    private SectorSpinnerContentContract.Spinner spinner;

    public SectorSpinnerContentPresenter(SectorSpinnerContentContract.Spinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void loadSpinnerData() {

    }
}
