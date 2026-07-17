import java.awt.*;

public class Projectile {
    private double projecX;
    private double projecY;
    private int pWidth, pHeight;
    private double velY, velX;
    private boolean arrived = false;


    public Projectile(double X, double Y, int clickX, int clickY, int velTotal) {
        projecX = X;
        projecY = Y;
        pWidth = 20;
        pHeight = 20;

        double dx = clickX - projecX;
        double dy = clickY - projecY;
        double hip = (dx*dx) + (dy*dy);
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
        pincel.setColor(Color.orange);
        pincel.fillOval((int) projecX, (int) projecY, pWidth, pHeight);

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
