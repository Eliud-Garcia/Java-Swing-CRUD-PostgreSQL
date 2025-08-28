

import controller.UserController;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        UserController uc = new UserController(mf);
    }
}