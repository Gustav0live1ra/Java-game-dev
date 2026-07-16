import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    int rectX = 0;
    int rectY;
    int rectWidth = 200;
    int rectHeight = 80;
    int rectSpeedX = 3;
    int rectSpeedY = 3;

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
    }

    public void start(){
        running = true;

        gameThread.start();
    }

    public void update(){

        rectX += rectSpeedX;
        rectY += rectSpeedY;

        //borda direita eixoX
        if(rectX + rectWidth >= getWidth() ){
            rectX = getWidth() - rectWidth;
            rectSpeedX = -rectSpeedX;
        }
        //borda esquerda eixoX
        else if(rectX <= 0){
            rectX = 0;
            rectSpeedX = -rectSpeedX;
        }

        //borda de baixo eixoY
        if(rectY + rectHeight >= getHeight()){
            rectY = getHeight() - rectHeight;
            rectSpeedY = -rectSpeedY;
        }
        //borda de cima eixoY
        else if(rectY <= 0){
            rectY = 0;
            rectSpeedY = -rectSpeedY;
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


}
