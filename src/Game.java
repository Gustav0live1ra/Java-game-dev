import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener {
    int rectX;
    int rectY;
    int rectWidth = 200;
    int rectHeight = 80;
    int rectSpeed = 2;
    boolean up,down,left,right;
    boolean running;
    Thread gameThread = new Thread(this);

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
        addKeyListener(this);
        setFocusable(true);
    }

    public void start(){
        running = true;

        gameThread.start();
    }

    public void update(){

        if(up){
            rectY-=rectSpeed;
        }
        if(down){
            rectY+=rectSpeed;
        }
        if(right){
            rectX+=rectSpeed;
        }
        if(left){
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
        pincel.setColor(Color.red);
//      int centralized_width = (getWidth()-200) / 2;
//      int centralized_height = (getHeight()-80) / 2;
        pincel.fillRect(rectX, rectY, rectWidth, rectHeight);
        pincel.drawRect(rectX, rectY, rectWidth, rectHeight);
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if(tecla == KeyEvent.VK_W){
            up = true;
        }else if(tecla == KeyEvent.VK_A){
            left = true;
        }else if(tecla == KeyEvent.VK_S){
            down = true;
        }else if(tecla == KeyEvent.VK_D){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        if(tecla == KeyEvent.VK_W){
            up = false;
        }else if(tecla == KeyEvent.VK_A){
            left = false;
        }else if(tecla == KeyEvent.VK_S){
            down = false;
        }else if(tecla == KeyEvent.VK_D){
            right = false;
        }
    }
}
