import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Projectile {
    private double projecX;
    private double projecY;
    private int pWidth, pHeight;
    private double velY, velX;
    private boolean arrived = false;
    BufferedImage sprite;


    public Projectile(double X, double Y, int clickX, int clickY, int velTotal) {
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Projétil.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        projecX = X;
        projecY = Y;
        pWidth = 15;
        pHeight = 15;

        double dx = clickX - projecX;
        double dy = clickY - projecY;
        double hip = (dx*dx) + (dy*dy); //distancia é igual a hipotenusa q interliga o click e o objeto
        hip = Math.sqrt(hip);
        dx = dx/hip;
        dy = dy/hip;

        this.velX = dx * velTotal;
        this.velY = dy * velTotal;
    }

    public void update(int width, int height){
        projecY += velY;
        projecX += velX;
        if( (projecX + pWidth > width || projecX < 0)  ||  (projecY + pHeight > height || projecY < 0) ){
            arrived = true;
        }
    }

    public void render(Graphics pincel){

        pincel.drawImage(sprite ,(int) projecX, (int) projecY, pWidth, pHeight, null);

    }


    public boolean isArrived() {
        return arrived;
    }

    public double getProjectileY() {
        return projecY;
    }

    public double getProjectileX() {
        return projecX;
    }

}
