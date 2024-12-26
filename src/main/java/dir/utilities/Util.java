package dir.utilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import dir.Main;
import dir.events.EventNode;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Util {

    static final String VIEWS_PATH = "views/";
    static final String PICTURES_PATH = "media/pictures/";
    static final String AUDIOS_PATH = "media/audios/";

    // Switch Scene Methods
    public static void switchTo(Node div, String fxml) {
        easeOut(div, 0.5);
        delay(0.5, () -> {
            try {
                setRoot(fxml);
            } catch (IOException e) {
            }
        });
    }

    // Basic Animations
    public static FadeTransition fade(Node node, double duration, double from, double to, boolean reverse, int cycle) {
        FadeTransition fade = new FadeTransition(Duration.seconds(duration), node);
        fade.setFromValue(from);
        fade.setToValue(to);
        fade.setCycleCount(cycle);
        fade.setAutoReverse(reverse);
        fade.play();
        return fade;
    }

    public static TranslateTransition translate(Node node, double duration, boolean horiz, double from, double to, boolean reverse, int cycle) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(duration), node);
        if (horiz) {
            translate.setFromX(from);
            translate.setToX(to);
        } else {
            translate.setFromY(from);
            translate.setToY(to);
        }
        translate.setCycleCount(cycle);
        translate.setAutoReverse(reverse);
        translate.play();
        return translate;
    }

    private static TranslateTransition translateBy(Node node, double duration, boolean horiz, double by, boolean auto_reverse, int cycle_count) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(duration), node);
        if (horiz) {
            translate.setByX(by);
        } else {
            translate.setByY(by);
        }
        translate.setAutoReverse(auto_reverse);
        translate.setCycleCount(cycle_count);
        translate.play();
        return translate;
    }

    // Transition Animations
    public static void easeIn(Node node, double duration) {
        double parent_height = -node.getBoundsInParent().getHeight();
        translate(node, duration, false, parent_height * 0.1, 0, false, 1);
        fade(node, duration, 0, 1, false, 1);
    }

    public static void easeOut(Node node, double duration) {
        fade(node, duration, 1.0, 0.0, false, 1);
        double startY = 0;
        double endY = node.getBoundsInParent().getHeight() * 0.1;
        translate(node, duration, false, startY, endY, false, 1);
    }

    public static void transitionWidth(Node node, double duration) {
        node.setScaleX(0);
        if (node.getScaleX() == 1) {
            return; // Check if transition is already applied
        }
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(duration), node);
        scaleTransition.setFromX(0);
        scaleTransition.setToX(1);
        scaleTransition.play();
    }

    // Background setup methods
    public static void setModifiedBg(ImageView bgImg, AnchorPane particlePane, Color color) {
        bindImageHeight(bgImg, 1, 0);
        bindAnchorPane(particlePane, 1, 0);
        createDustEffect(particlePane, Main.scene.getWidth(), Main.scene.getHeight(), color, 3, 7);
    }

    // Bind methods
    public static void bindImageHeight(ImageView img, double multiplier, double addend) {
        img.fitHeightProperty().bind(Main.scene.heightProperty().multiply(multiplier).add(addend));
    }

    public static void bindPane(Region pane, double multiplier, double addend) {
        pane.prefWidthProperty().bind(Main.scene.widthProperty().multiply(multiplier).add(addend));
        pane.prefHeightProperty().bind(Main.scene.heightProperty().multiply(multiplier).add(addend));
    }

    public static void bindStackPane(StackPane pane, double multiplier, double addend) {
        bindPane(pane, multiplier, addend);
    }

    public static void bindBorderPane(BorderPane pane, double multiplier, double addend) {
        bindPane(pane, multiplier, addend);
    }

    public static void bindVBox(VBox pane, double multiplier, double addend) {
        bindPane(pane, multiplier, addend);
    }

    public static void bindHBox(HBox pane, double multiplier, double addend) {
        bindPane(pane, multiplier, addend);
    }

    public static void bindAnchorPane(AnchorPane pane, double multiplier, double addend) {
        bindPane(pane, multiplier, addend);
    }

    // Apply methods
    public static void applyShadow(Node imageView, double radius, double spread, double offsetX, double offsetY, Color color) {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(radius);
        shadow.setSpread(spread);
        shadow.setOffsetX(offsetX);
        shadow.setOffsetY(offsetY);
        shadow.setColor(color);

        imageView.setEffect(shadow);
    }

    public static void oscillateX(Node imageView, double duration, int range) {
        translateBy(imageView, duration, true, range, true, TranslateTransition.INDEFINITE);
    }

    public static void oscillateY(Node imageView, double duration, int range) {
        translateBy(imageView, duration, false, range, true, TranslateTransition.INDEFINITE);
    }

    public static void blinkFade(Node imageView, double duration, boolean active) {
        if (active) {
            fade(imageView, duration, 0, 1, true, -1);
        }
    }

    public static void fadeIn(Node imageView, double duration) {
        fade(imageView, duration, 0, 1, false, 1);
    }

    public static void fadeOut(Node imageView, double duration) {
        fade(imageView, duration, 1, 0, false, 1);
    }

    public static void playAudio(MediaPlayer mp, boolean loop) {
        int mode = (loop) ? MediaPlayer.INDEFINITE : 1;
        Main.mp.setCycleCount(mode);
        Main.mp.play();
    }

    // Create Methods
    public static Scene createScene(String fxmlFile, double width, double height) {
        try {
            return new Scene(loadFXML(fxmlFile), width, height);
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception
            return null;
        }
    }

    public static MediaPlayer createMediaPlayer(String mp4File) {
        return new MediaPlayer(new Media(Main.class.getResource(AUDIOS_PATH + mp4File + ".mp4").toExternalForm()));
    }

    public static ImageView createImage(String pngFile) {
        ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream(PICTURES_PATH + pngFile + ".png")));
        imageView.setFitWidth(900); // Set appropriate width
        imageView.setFitHeight(600); // Set appropriate height
        return imageView;
    }

    // Other effects
    public static void createDustEffect(AnchorPane particlePane, double backgroundWidth, double backgroundHeight, Color color, double minSize, double sizeRange) {
        Timeline particleTimeline = new Timeline(new KeyFrame(Duration.millis(50), event -> spawnParticle(particlePane, color, minSize, sizeRange)));
        particleTimeline.setCycleCount(Timeline.INDEFINITE);
        particleTimeline.play();

        particlePane.widthProperty().addListener((obs, oldVal, newVal) -> {
            particleTimeline.stop();
            particleTimeline.play();
        });
        particlePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            particleTimeline.stop();
            particleTimeline.play();
        });
    }

    private static void spawnParticle(AnchorPane particlePane, Color color, double minSize, double sizeRange) {
        double backgroundWidth = particlePane.getWidth();
        double backgroundHeight = particlePane.getHeight();

        Rectangle particle = new Rectangle(Math.random() * minSize + sizeRange, Math.random() * minSize + sizeRange, color);
        particle.setOpacity(0.8);

        particle.setTranslateX(Math.random() * backgroundWidth);
        particle.setTranslateY(Math.random() * backgroundHeight);

        double speedX = (Math.random() - 0.5) * 2;

        particlePane.getChildren().add(particle);

        TranslateTransition moveTransition = translateBy(particle, 10.0, false, -backgroundHeight, false, 1);
        moveTransition.setByX(speedX * 30);

        ParallelTransition parallelTransition = new ParallelTransition(
                moveTransition,
                fade(particle, 1, 1, 0, false, 1)
        );

        parallelTransition.setOnFinished(e -> particlePane.getChildren().remove(particle));
        parallelTransition.play();
    }

    // Timing methods
    public static void delay(double seconds, Runnable action) {
        System.out.println("Delaying for " + seconds + " seconds");
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> {
            System.out.println("Delay finished");
            action.run();
        });
        pause.play();
    }

    // Others
    public static void setRoot(String fxml) throws IOException {
        Main.scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(Main.class.getResource(VIEWS_PATH + fxml + ".fxml"));
    }

    public static void addChild(StackPane root, String fxmlPath, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(controller.getClass().getResource("/dir/views/" + fxmlPath + ".fxml"));
        loader.setController(controller);
        Parent child = loader.load();
        root.getChildren().add(child);
    }

    public static void addSpacebarListener(Scene scene, Runnable action) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                action.run();
            }
        });
    }

    public static void addLeftRightKeyListeners(Scene scene, Runnable incrementAction, Runnable decrementAction) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                incrementAction.run();
            } else if (event.getCode() == KeyCode.LEFT) {
                decrementAction.run();
            }
        });
    }

    public static <T> boolean addChildOnce(StackPane root, String path, T controller, boolean boolVar) {
        if (!boolVar) {
            try {
                addChild(root, path, controller);
                return true;
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception
                return false;
            }
        }
        return false;
    }

    public static void removeFromStackPane(Node node) {
        ((StackPane) node.getParent()).getChildren().remove(node);
    }

    public static void removeFromParent(Node node) {
        if (node.getParent() != null) {
            try {
                Method getChildrenMethod = node.getParent().getClass().getMethod("getChildren");
                @SuppressWarnings("unchecked")
                ObservableList<Node> children = (ObservableList<Node>) getChildrenMethod.invoke(node.getParent());
                children.remove(node);
            } catch (Exception e) {
                throw new UnsupportedOperationException("Unsupported parent type: " + node.getParent().getClass().getName(), e);
            }
        }
    }
}
