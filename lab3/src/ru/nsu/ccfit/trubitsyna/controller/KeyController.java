package ru.nsu.ccfit.trubitsyna.controller;

import ru.nsu.ccfit.trubitsyna.gameException.GameException;
import ru.nsu.ccfit.trubitsyna.model.Directions;
import ru.nsu.ccfit.trubitsyna.model.GameModel;
import ru.nsu.ccfit.trubitsyna.view.ViewGame;
import ru.nsu.ccfit.trubitsyna.view.ViewState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyController extends KeyAdapter {
    private final GameModel model;
    private final ViewGame view;
    private static final int MAX_DIRECT = 2;

    public KeyController(GameModel model, ViewGame viewGame) throws IOException, GameException {
        this.model = model;
        this.view = viewGame;
        viewGame.setModel(model);
        this.view.addKeyListener(this);
    }

    public void execute() throws IOException, GameException {
        model.startRound();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Directions lastDirections = model.getDirection();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (!model.isEnd() && !model.isPause()) {
                    if (model.getSizeArrayDirections() < MAX_DIRECT) {
                        if (lastDirections != Directions.UP && lastDirections != Directions.DOWN) {
                            model.changeDirection(Directions.UP);
                        }
                        System.out.println("up");
                    }
                }
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (!model.isEnd() && !model.isPause()) {
                    if (model.getSizeArrayDirections() < MAX_DIRECT) {
                        if (lastDirections != Directions.UP && lastDirections != Directions.DOWN) {
                            model.changeDirection(Directions.DOWN);
                        }
                        System.out.println("down");
                    }
                }
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (!model.isEnd() && !model.isPause()) {
                    if (model.getSizeArrayDirections() < MAX_DIRECT) {
                        if (lastDirections != Directions.LEFT && lastDirections != Directions.RIGHT) {
                            model.changeDirection(Directions.LEFT);
                        }
                        System.out.println("left");
                    }
                }
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (!model.isEnd() && !model.isPause()) {
                    if (model.getSizeArrayDirections() < MAX_DIRECT) {
                        if (lastDirections != Directions.LEFT && lastDirections != Directions.RIGHT) {
                            model.changeDirection(Directions.RIGHT);
                        }
                    }
                    System.out.println("right");
                }
                break;
            case KeyEvent.VK_P:
                if (!model.isPause()) {
                    model.setOnPause();
                } else {
                    model.offPause();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                view.dispose();
                break;
            case KeyEvent.VK_F:
                model.setOnPause();
                model.setNewGame();
                String name = view.getUserName();
                if (name == null) {
                    model.setGameState(ViewState.MENU);
                    model.offPause();
                    return;
                }
                model.resetGame(name);
                model.offPause();
                model.setGameState(ViewState.GAME);
                break;
            case KeyEvent.VK_B:
                model.setGameState(ViewState.MENU);
                model.setNewGame();
                break;

        }
    }

}
