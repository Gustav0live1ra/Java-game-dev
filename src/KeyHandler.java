import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean up, down, left, right;

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
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
