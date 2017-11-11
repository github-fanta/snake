package com.snake.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.snake.SnakeListener.SnakeListener;
import com.snake.enterties.Food;
import com.snake.enterties.Ground;
import com.snake.enterties.Snake;
import com.snake.view.GamePanel;

public class Controller extends KeyAdapter implements SnakeListener{

	private Snake snake;
	private Ground ground;
	private Food food;
	private GamePanel gamePanel;
	public Controller(Snake snake, Ground ground, Food food, GamePanel gamePanel) {
		
		this.snake = snake;
		this.ground = ground;
		this.food = food;
		this.gamePanel = gamePanel;
	}

	//Controller任务一：处理按键事件
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		}
	}

	//Controller任务二：处理逻辑
	@Override
	public void snakeMoved(Snake snake) {
		//判断蛇是否吃到食物
		if (food.isSnakeEatFood(snake)) {
			//向蛇发出吃食物的命令
			snake.eatFood();
			//向食物发出被吃的命令——放置新食物的位置
			food.newFood(ground);

		}
		
		//咬到石头
		if (ground.isSnakeEatRock(snake)) {
			snake.die();
		}
		//咬到身体
		if (snake.isEatBody()) {
			snake.die();
		}
		gamePanel.display(snake, food, ground);
	}
	
	

	public void newGame() {
		snake.start();
		food.newFood(ground);
	}

}
