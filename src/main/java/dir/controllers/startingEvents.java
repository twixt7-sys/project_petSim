package dir.controllers;

import dir.utilities.Util;
import javafx.scene.paint.Color;

public class startingEvents {

    private final startingScrController controller;
    private boolean isTitleAdded = false;
    private boolean isDiv1Added = false;
    private boolean isEv1Added = false;

    public startingEvents(startingScrController controller) {
        this.controller = controller;
    }

    public void entrance0() {
        System.out.println("entrance0 called");

        Util.fadeIn(controller.bg_img, 6);
        Util.fadeIn(controller.particle_pane, 30);
        Util.oscillateX(controller.bg_img, 20, 450);
    }

    public void event0() {
        System.out.println("Starting event0");

        Util.setModifiedBg(controller.bg_img, controller.particle_pane, new Color(0.7, 1, 0.5, 0.7));
        Util.bindImageHeight(controller.bg_img, 1, 0);
        Util.bindAnchorPane(controller.particle_pane, 1, 0);

        Util.addChildOnce(controller.root, "fxcomponents/titles", controller, isTitleAdded);
        isTitleAdded = true;
        Util.bindImageHeight(controller.pstp_img, 0.04, 0);
        Util.bindImageHeight(controller.title_img, 0.35, 0);
        Util.applyShadow(controller.title_img, 0, 5, 5, 5, new Color(0, 0, 0, 0.25));

        controller.title_img.setOpacity(0);
        controller.pstp_img.setOpacity(0);

        Util.delay(5, () -> {
            Util.fadeIn(controller.title_img, 3);
            Util.oscillateY(controller.title_img, 1.5, 10);
            Util.delay(4, () -> {
                Util.fadeIn(controller.pstp_img, 1);
                Util.blinkFade(controller.pstp_img, 1, true);
            });
        });
    }

    public void exit0() {
        System.out.println("exit0 called");
        Util.easeOut(controller.title_img, 0.5);
        Util.easeOut(controller.pstp_img, 0.5);
    }

    public void entrance1() {
        System.out.println("entrance1 called");
        Util.transitionWidth(controller.div1, 1);
    }

    public void event1() {
        System.out.println("Starting event1");
        Util.addChildOnce(controller.root, "interfaces/div1", controller, isDiv1Added);
        isDiv1Added = true;

        Util.delay(1, () -> {
            Util.addChildOnce(controller.div1, "fxcomponents/ev1", controller, isEv1Added);
            isEv1Added = true;
            Util.fadeIn(controller.comps1, 1);
        });
    }

    public void exit1() {
        System.out.println("exit1 called");
        Util.easeOut(controller.div1, 0.5);
    }
}