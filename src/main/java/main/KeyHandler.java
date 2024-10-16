package main;

import entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {
    public boolean up, down, right, left;
    private final Map<Integer, Runnable> keyPressedActions = new HashMap<>();
    private final Map<Integer, Runnable> keyReleasedAction = new HashMap<>();

    public KeyHandler(){
        keyPressedActions.put(KeyEvent.VK_W, () -> up = true);
        keyPressedActions.put(KeyEvent.VK_A, () -> left = true);
        keyPressedActions.put(KeyEvent.VK_S, () -> down = true);
        keyPressedActions.put(KeyEvent.VK_D, () -> right = true);

        keyReleasedAction.put(KeyEvent.VK_D, () -> right = false);
        keyReleasedAction.put(KeyEvent.VK_W, () -> up = false);
        keyReleasedAction.put(KeyEvent.VK_S, () -> down = false);
        keyReleasedAction.put(KeyEvent.VK_A, () -> left = false);
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
