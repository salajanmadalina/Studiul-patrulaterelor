package Presenter;

import Model.Patrulater;
import View.IGuest;
import java.util.ArrayList;

public class GuestPresenter {
    private IGuest iGuest;

    public GuestPresenter(IGuest guest) {
        this.iGuest = guest;
    }

    public void setFrameRegister(){
        iGuest.changeFrameToRegister();
    }

    public void giveInfoToView(){
        ArrayList<Integer> info = iGuest.getInfoFromView();
        Patrulater patrulater= new Patrulater(info);
        ArrayList<Integer> coords = patrulater.cercuri();

        iGuest.giveInformation(patrulater.toString(), coords);
    }

    public void setFrameMain(){
        iGuest.changeFrameToMain();
    }
}
