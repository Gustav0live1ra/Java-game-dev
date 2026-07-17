import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable, MouseListener {
    int rectX;
    int rectY;
    int rectWidth = 200;
    int rectHeight = 80;
    int rectSpeed = 2;
    int clickX=-1000, clickY=-1000;

    boolean running;
    Thread gameThread = new Thread(this);
    KeyHandler keyHandler = new KeyHandler();
    ArrayList<Projectile> projectiles = new ArrayList<>();


    public static void main(String[] args) {

        Game game = new Game(800,600);
        new Window("My First Game", game);

        game.start();

    }

    public Game(int width, int height){

        Dimension dimension = new Dimension(width, height);
        this.setPreferredSize(dimension);
        rectY = (height - rectHeight) / 2;
        rectX = (width - rectWidth) / 2;
        addKeyListener(keyHandler);
        setFocusable(true);
        addMouseListener(this);
    }

    public void start(){
        running = true;

        gameThread.start();
    }

    public void update(){

        for(Projectile p : projectiles){
            p.update(getWidth(), getHeight());
            if(p.isArrived()){
               System.out.println("projéteis na tela: " +( projectiles.size()-1));
            }

        }
        projectiles.removeIf(Projectile::isArrived);

        if(keyHandler.isUp()){
            rectY-=rectSpeed;
        }
        if(keyHandler.isDown()){
            rectY+=rectSpeed;
        }
        if(keyHandler.isRight()){
            rectX+=rectSpeed;
        }
        if(keyHandler.isLeft()){
            rectX-=rectSpeed;
        }

        //borda direita eixoX
        if(rectX + rectWidth >= getWidth() ){
            rectX = getWidth() - rectWidth;

        }
        //borda esquerda eixoX
        else if(rectX <= 0){
            rectX = 0;

        }

        //borda de baixo eixoY
        if(rectY + rectHeight >= getHeight()){
            rectY = getHeight() - rectHeight;

        }
        //borda de cima eixoY
        else if(rectY <= 0){
            rectY = 0;

        }

    }

    public void render() {
        if (getBufferStrategy() == null) {
            createBufferStrategy(3);
            return;
        }
        //liberado para desenhar

        BufferStrategy bs = getBufferStrategy();
        Graphics pincel = bs.getDrawGraphics();
        pincel.setColor(Color.black);
        pincel.fillRect(0, 0, getWidth(), getHeight());
//      int centralized_width = (getWidth()-200) / 2;
//      int centralized_height = (getHeight()-80) / 2;
        pincel.setColor(Color.red);
        pincel.fillRect(rectX, rectY, rectWidth, rectHeight);
        pincel.drawRect(rectX, rectY, rectWidth, rectHeight);
        for(Projectile p : projectiles){
            p.render(pincel);
        }
        pincel.dispose();
        bs.show();
    }
    public void run(){

        long previuostime = System.currentTimeMillis();
        int frames=0;
        while(running){

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                System.out.println("houve um erro, por favor tente novamente.");
                break;
            }

            //atualizando lógica...
            update();

            //renderizando...
            render();

            //contabilizando frame renderizado
            frames++;

            long currenttime = System.currentTimeMillis();

            if(currenttime - previuostime >= 1000){

                System.out.println("fps: "+ frames);
                frames=0;
                previuostime = currenttime;

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickX = e.getX();
        clickY = e.getY();
        projectiles.add(new Projectile(rectX + ((double) rectWidth / 2), rectY + ((double) rectHeight /2), clickX, clickY, 5));
        System.out.println("projéteis na tela: "+projectiles.size());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
