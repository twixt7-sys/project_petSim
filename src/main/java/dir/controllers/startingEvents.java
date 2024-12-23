package dir.controllers;

import dir.utilities.Util;
import javafx.scene.paint.Color;

public class startingEvents {

    private final startingScrController controller;

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
        startingScrController.isAdded[0] = Util.addChildOnce(controller.root, "fxcomponents/titles", controller, startingScrController.isAdded[0]);
        System.out.println("isAdded[0]: " + startingScrController.isAdded[0]);

        Util.setModifiedBg(controller.bg_img, controller.particle_pane, new Color(0.7, 1, 0.5, 0.7));
        Util.bindImageHeight(controller.bg_img, 1, 0);
        Util.bindImageHeight(controller.pstp_img, 0.04, 0);
        Util.bindImageHeight(controller.title_img, 0.35, 0);
        Util.bindAnchorPane(controller.particle_pane, 1, 0);

        Util.applyShadow(controller.title_img, 0, 5, 5, 5, new Color(0, 0, 0, 0.25));

        entrance0();

        controller.title_img.setOpacity(0);
        controller.pstp_img.setOpacity(0);

        Util.delay(5, () -> {
            System.out.println("Event0 delay 5 seconds finished");
            Util.fadeIn(controller.title_img, 3);
            Util.oscillateY(controller.title_img, 1.5, 10);
            Util.delay(4, () -> {
                System.out.println("Event0 delay 4 seconds finished");
                Util.fadeIn(controller.pstp_img, 1);
                Util.blinkFade(controller.pstp_img, 1, startingScrController.blinkfade);
                System.out.println("Finished event0");
                startingScrController.isTransitioning = false;
            });
        });
    }

    public void exit0(double delayTime, Runnable onFinish) {
        System.out.println("exit0 called with delayTime: " + delayTime);
        if (controller.title_img != null && controller.pstp_img != null) {
            Util.easeOut(controller.title_img, delayTime);
            Util.easeOut(controller.pstp_img, delayTime);
            startingScrController.blinkfade = false;
            Util.delay(delayTime, () -> {
                controller.pstp_img.setOpacity(1);
                controller.pstp_img.getTransforms().clear();
                if (controller.container.getParent() != null) {
                    Util.removeFromStackPane(controller.container);
                }
                Util.delay(delayTime, onFinish);
            });
        } else {
            Util.delay(delayTime, onFinish);
        }
    }

    public void entrance1() {
        System.out.println("entrance1 called");
        if (controller.div1 != null) {
            Util.transitionWidth(controller.div1, 1);
            startingScrController.isAdded[1] = true; // Ensure isAdded[1] is set to true after entrance1
            System.out.println("isAdded[1] set to true");
        }
    }

    public void event1() {
        System.out.println("Starting event1");
        startingScrController.isAdded[1] = Util.addChildOnce(controller.root, "interfaces/div1", controller, startingScrController.isAdded[1]);
        System.out.println("isAdded[1]: " + startingScrController.isAdded[1]);
        if (controller.div1 != null) {
            if (!startingScrController.isAdded[1]) {
                entrance1();
            }
            Util.delay(1, () -> {
                System.out.println("Event1 delay 1 second finished");
                startingScrController.isAdded[2] = Util.addChildOnce(controller.div1, "fxcomponents/ev1", controller, startingScrController.isAdded[2]);
                System.out.println("isAdded[2]: " + startingScrController.isAdded[2]);
                Util.fadeIn(controller.comps1, 1);
                System.out.println("Finished event1");
                startingScrController.isTransitioning = false;
            });
        } else {
            System.out.println("Finished event1");
            startingScrController.isTransitioning = false;
        }
    }

    public void exit1(double delayTime, Runnable onFinish) {
        System.out.println("exit1 called with delayTime: " + delayTime);
        if (controller.div1 != null && controller.div1.getParent() != null) {
            Util.easeOut(controller.div1, delayTime);
            Util.delay(delayTime, () -> {
                Util.removeFromStackPane(controller.div1);
                onFinish.run();
            });
        } else {
            onFinish.run();
        }
    }

}
