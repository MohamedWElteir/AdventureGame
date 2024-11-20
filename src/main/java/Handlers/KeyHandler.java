package Handlers;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {
    public boolean up, down, right, left, speedUp;
    private final Map<Integer, Runnable> keyPressedActions = new HashMap<>();
    private final Map<Integer, Runnable> keyReleasedAction = new HashMap<>();
    private void KeyPressedReleased(Integer key, Runnable pressed, Runnable released){
        keyPressedActions.put(key, pressed);
        keyReleasedAction.put(key, released);
    }

    public KeyHandler(){
        KeyPressedReleased(KeyEvent.VK_W, () -> up = true, () -> up = false);
        KeyPressedReleased(KeyEvent.VK_S, () -> down = true, () -> down = false);
        KeyPressedReleased(KeyEvent.VK_A, () -> left = true, () -> left = false);
        KeyPressedReleased(KeyEvent.VK_D, () -> right = true, () -> right = false);

        KeyPressedReleased(KeyEvent.VK_SHIFT, () -> speedUp = true, () -> speedUp = false);

        KeyPressedReleased(KeyEvent.VK_UP, () -> up = true, () -> up = false);
        KeyPressedReleased(KeyEvent.VK_DOWN, () -> down = true, () -> down = false);
        KeyPressedReleased(KeyEvent.VK_LEFT, () -> left = true, () -> left = false);
        KeyPressedReleased(KeyEvent.VK_RIGHT, () -> right = true, () -> right = false);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressedActions.getOrDefault(e.getKeyCode(), () -> {}).run();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyReleasedAction.getOrDefault(e.getKeyCode(), () -> {}).run();
    }


}
