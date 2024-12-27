package dir.controllers;

import dir.Main;
import dir.utilities.Util;
import javafx.scene.paint.Color;

public class gameEvents {

    private final gameScrController controller;

    public gameEvents(gameScrController controller) {
        this.controller = controller;
    }

    public void entranceGameEvent1() {
        System.out.println("Game Event 1 Entrance");

        Util.fadeIn(controller.bg_img, 6);
        Util.fadeIn(controller.particle_pane, 30);
        Util.oscillateX(controller.bg_img, 20, 450);

        Main.mp = Util.createMediaPlayer("bgm2");
        Main.mp.play();
        Util.easeInMusic(Main.mp, 1);
    }

    public void bodyGameEvent1() {
        System.out.println("Game Event 1 Body");

        Util.setModifiedBg(controller.bg_img, controller.particle_pane, new Color(0.7, 1, 0.5, 0.7));
        Util.bindImageHeight(controller.bg_img, 1, 0);
        Util.bindAnchorPane(controller.particle_pane, 1, 0);
    }

    public void exitGameEvent1() {
        System.out.println("Game Event 1 Exit");
        // Add exit logic here
    }

    // Add more event handlers as needed
}
