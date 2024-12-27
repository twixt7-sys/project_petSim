package dir.controllers;

import dir.utilities.Util;
import javafx.scene.paint.Color;

public class startingEvents {

    private final startingScrController controller;

    // flags
    private boolean isTitleAdded = false;
    private boolean isDiv1Added = false;
    private boolean isEv1Added = false;
    private boolean isChoosePetAdded = false;
    private boolean isEv2Added = false;
    private boolean isEnterNameAdded = false;
    private boolean isEv3Added = false;

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

        isTitleAdded = Util.addChildOnce(controller.root, "fxcomponents/titles", controller, isTitleAdded);
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
        double delay = 0.5;
        Util.easeOut(controller.title_img, delay);
        Util.easeOut(controller.pstp_img, delay);
        Util.delay(delay, () -> Util.removeFromParent(controller.pstp_img));
        isTitleAdded = false;
    }

    public void entrance1() {
        System.out.println("entrance1 called");
        isDiv1Added = Util.addChildOnce(controller.root, "interfaces/div1", controller, isDiv1Added);
        isEv1Added = Util.addChildOnce(controller.div1, "fxcomponents/ev1", controller, isEv1Added);
        controller.comps1.setOpacity(0);
        Util.transitionWidth(controller.div1, 0.5);
    }

    public void event1() {
        System.out.println("Starting event1");
        Util.delay(1, () -> Util.fadeIn(controller.comps1, 1));
    }

    public void exit1() {
        System.out.println("exit1 called");
        Util.easeOut(controller.div1, 0.5);
        Util.delay(0.5, () -> Util.removeFromParent(controller.div1));
        isDiv1Added = false;
        isEv1Added = false;
    }

    public void entranceChoosePet() {
        System.out.println("entranceChoosePet called");
        isChoosePetAdded = Util.addChildOnce(controller.root, "interfaces/div1", controller, isChoosePetAdded);
        isEv2Added = Util.addChildOnce(controller.div1, "fxcomponents/choosepet", controller, isEv2Added);

        controller.vb1.setOpacity(0);
        controller.vb2.setOpacity(0);

        Util.transitionWidth(controller.div1, 0.5);
    }

    public void eventChoosePet() {
        System.out.println("Starting eventChoosePet");
        Util.oscillateY(controller.vb2, 1.5, 10);
        Util.bindImageHeight(controller.dog_img, 0.25, 0);
        Util.bindImageHeight(controller.cat_img, 0.25, 0);
        Util.bindImageHeight(controller.frog_img, 0.25, 0);
        Util.delay(1, () -> {
            Util.fadeIn(controller.vb1, 1);
            Util.fadeIn(controller.vb2, 1);
        });
    }

    public void exitChoosePet() {
        System.out.println("exitChoosePet called");
        Util.easeOut(controller.div1, 0.5);
        Util.delay(0.5, () -> Util.removeFromParent(controller.div1));
        isChoosePetAdded = false;
        isEv2Added = false;
    }

    public void entranceEnterPetName() {
        System.out.println("entranceEnterPetName called");
        isEnterNameAdded = Util.addChildOnce(controller.root, "interfaces/div1", controller, isEnterNameAdded);
        isEv3Added = Util.addChildOnce(controller.div1, "fxcomponents/entername", controller, isEv3Added);
        controller.vb_entername.setOpacity(0);
        controller.tf.setOpacity(0);

        Util.transitionWidth(controller.div1, 0.5);
    }

    public void eventEnterPetName() {
        System.out.println("eventEnterPetName called");
        Util.delay(1, () -> {
            Util.fadeIn(controller.vb_entername, 1);
            Util.fadeIn(controller.tf, 1);
        });
    }

    public void exitEnterPetName() {
        System.out.println("exitEnterPetName called");
        Util.easeOut(controller.div1, 0.5);
        Util.delay(0.5, () -> Util.removeFromParent(controller.div1));
        isEnterNameAdded = false;
        isEv3Added = false;
    }

    public void entranceSwitchToGame() {

    }

    public void eventSwitchToGame() {
        System.out.println("eventSwitchToGame called");
        Util.fadeOut(controller.root, 1);
        Util.delay(1, () -> {
            Util.removeFromParent(controller.root);
            controller.switchToGame();
        });
    }

    public void exitSwitchToGame() {

    }
}
