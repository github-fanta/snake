package com.snake.enterties;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import com.snake.utils.Global;

public class Food extends Point{

	
	public void newFood(Ground ground) {
		Point p = getPoint();
		do {
			p = getPoint();
		} while (ground.isFoodAndGroundOverlapped(p));
		this.setLocation(p);
	}
	
	public Point getPoint() {

		Random random = new Random();
		int x = random.nextInt(Global.WIDTH);
		int y = random.nextInt(Global.HEIGHT);
		return new Point(x, y);
	}
	
	//判断蛇是否吃到自己
	public boolean isSnakeEatFood(Snake snake) {
		System.out.println("Food' isSnakeEatFood");
		//Point类内部已经重写了equals 即通过坐标来比较是否相同，而不是两个对象的地址。和String类一样
		return this.equals(snake.getHead());
	}


	public void drawMe(Graphics g) {
		System.out.println("Food's drawMe");
		g.setColor(Color.BLUE);
		g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
	
	
}
