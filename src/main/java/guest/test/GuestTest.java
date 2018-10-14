package guest.test;

import guest.config.GlobalConfig;
import guest.gui.GuestFrame;
import guest.gui.GuiResource;

public class GuestTest {


    public static void main(String[] args) {
        try {
            GlobalConfig.initGlobalConfig();
            GuiResource.initComponentResources();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return;
        }
        GuestFrame cf = new GuestFrame();
        cf.setVisible(true);
    }
}
