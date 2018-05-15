package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;

public class MilkGlassPane extends Region {

    // circle container
    private final Pane container;

    // blur (milk glass effect)
    private final GaussianBlur blur;
    private final double initialBlur = 18;

    // background image
    private WritableImage image;


    public MilkGlassPane(Pane container) {

        // milk glass effect:
        // set the blur and color adjust effects
        blur = new GaussianBlur(24);
        setEffect(blur);
        //blur.setInput(new ColorAdjust(0, 0, 0.1, 0.0));

        // circle container
        this.container = container;

        // update the background every pulse
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long time) {
                updateBackground();
            }
        };

        timer.start();

    }

    /**
     * Updates the background. Create a snapshot of the circle container that
     * fits exactly this pane's bounds and updates the background.
     */
    private void updateBackground() {

        // return null if this pane is invisible
        if (getWidth() < 1
                || getHeight() < 1
                || this.getOpacity() == 0) {
            return;
        }

        // creates a new writable image and update background if dimensions do not match
        if (image == null
                || getWidth() != image.getWidth()
                || getHeight() != image.getHeight()) {

            image = new WritableImage(
                    (int) (this.getWidth()),
                    (int) (this.getHeight()));

            // create the backgrouhnd image
            BackgroundImage backgroundImg = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

            setBackground(new Background(backgroundImg));
        }

        // x, y location of this pane
        double x = this.localToScene(this.getBoundsInLocal()).getMinX();
        double y = this.localToScene(this.getBoundsInLocal()).getMinY()+20;


        // create the snapshot parameters (defines viewport)
        SnapshotParameters sp = new SnapshotParameters();
        Rectangle2D rect = new Rectangle2D(
                x,
                y,
                getWidth(),
                getHeight());
        sp.setViewport(rect);

        // create the snapshot
        container.snapshot(sp, image);

    }
}