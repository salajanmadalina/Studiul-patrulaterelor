package View;

import java.util.ArrayList;

public interface IGuest {
    public void changeFrameToRegister();
    public void giveInformation(String info, ArrayList<Integer> coords);
    public ArrayList<Integer> getInfoFromView();
    public void changeFrameToMain();

}
