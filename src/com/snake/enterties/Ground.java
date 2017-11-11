package com.snake.enterties;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.snake.utils.Global;

public class Ground {

	//此处分别表示横纵坐标
	private int[][]  rocks = new int[Global.WIDTH][Global.HEIGHT];
	
	public int[][] getRocks() {
		return rocks;
	}

	public Ground(){
		//上下设置石头
		for(int x=0; x<Global.WIDTH; x++) {
			rocks[x][0] = 1;
			rocks[x][Global.HEIGHT-1] = 1;
		}
	}
	
	
	public boolean isSnakeEatRock(Snake snake) {
		System.out.println("Ground's isSnakeEatRock");
		for(int x=0; x<Global.WIDTH; x++) {
			for(int y=0; y<Global.WIDTH; y++) {
				if (rocks[x][y] == 1 && snake.getHead().getX() == x && snake.getHead().getY() == y) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	//判断新生成的食物和石头是否重合
	public boolean isFoodAndGroundOverlapped(Point foodPoint) {
		
		for(int x=0; x<Global.WIDTH; x++) {
			for(int y=0; y<Global.HEIGHT; y++) {
				if (rocks[x][y] == 1 && foodPoint.getX() == x && foodPoint.getY() == y) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void drawMe(Graphics g) {
		System.out.println("Ground's drawMe");
		g.setColor(Color.DARK_GRAY);
		for(int x=0; x<Global.WIDTH; x++) {
			for(int y=0; y<Global.HEIGHT; y++) {
				if (rocks[x][y] == 1) {
					g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
}
