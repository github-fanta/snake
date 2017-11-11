package com.snake.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.snake.enterties.Food;
import com.snake.enterties.Ground;
import com.snake.enterties.Snake;
import com.snake.utils.Global;

public class GamePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 372764524597902430L;
	private Snake snake;
	private Food food;
	private Ground ground;
	
	public void display(Snake snake,Food food,Ground ground) {
		
		System.out.println("GamePanel's display");
		this.ground = ground;
		this.food = food;
		this.snake = snake;
		this.repaint();
		
	}
	
	//display方法最终会调用paintComponet
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.WIDTH*Global.CELL_SIZE, Global.HEIGHT*Global.CELL_SIZE);
		if (snake != null) {
			this.food.drawMe(g);
			this.ground.drawMe(g);
			this.snake.drawMe(g);
		}
	}

	
	
}
