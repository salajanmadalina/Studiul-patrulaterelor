package View;

import javax.swing.*;

public interface IMainView {
    public String getUsername();
    public String getPassword();
    public void changeFrameToGuest();
    public void changeFrameToUser();
    public void changeFrameToAdmin();

}
