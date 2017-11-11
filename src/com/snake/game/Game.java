package com.snake.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.snake.controller.Controller;
import com.snake.enterties.Food;
import com.snake.enterties.Ground;
import com.snake.enterties.Snake;
import com.snake.utils.Global;
import com.snake.view.GamePanel;

public class Game {
	
	public static void main(String args[]) {
		
		Snake snake = new Snake();
		Food  food  = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(snake, ground, food, gamePanel);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.setSize(Global.WIDTH*Global.CELL_SIZE, Global.HEIGHT*Global.CELL_SIZE);
		frame.setSize(Global.WIDTH*Global.CELL_SIZE+15, Global.HEIGHT*Global.CELL_SIZE+35);
		frame.add(gamePanel, BorderLayout.CENTER);
		
		//给gamePanel添加上监听器
		gamePanel.addKeyListener(controller);
		//给frame也添加上监听器
		frame.addKeyListener(controller);
		
		//给蛇也添加上监听器，主要用于蛇移动以后执行的动作
		snake.addSnakeListener(controller);
		
		frame.setVisible(true);
		controller.newGame();

	}
}
