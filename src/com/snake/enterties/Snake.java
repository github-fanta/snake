package com.snake.enterties;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.snake.SnakeListener.SnakeListener;
import com.snake.utils.Global;

public class Snake {
	
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = -2;
	public static final int RIGHT = 2;
	private int oldDirection;
	private int newDirection;
	private boolean life;
	
	private Point oldTail;
	
	private LinkedList<Point> body = new LinkedList<Point>();
	//初始化生成蛇
	public Snake() {
		init();
	}
	private void init() {
		int x = Global.WIDTH/2;
		int y = Global.HEIGHT/2;
		
		for (int i = 0; i < 3; i++) {
			body.addLast(new Point(x-i, y));
		}
		
		oldDirection = newDirection = RIGHT;
		life = true;
	}
	
	//设置监听器集合
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	
	//蛇如何移动
	public void move() {
		System.out.println("Snake's move");
		
		//不管键盘按了多少次，造成newDirection更改了多少次，直到运行的时候才会把最好改变的方向赋值给新方向。
		if (oldDirection + newDirection != 0) {
			oldDirection = newDirection;
		}
		
		//1去尾
		oldTail = body.removeLast();
		
		//根据方向确定向哪个方向加头
		int x = (int) body.getFirst().getX();
		int y = (int) body.getFirst().getY();
		
		switch (oldDirection) {
		case UP:
			y--;
			if (y < 0) {
				y = Global.HEIGHT-1;
			}
			break;
		case DOWN:
			y++;
			if (y > Global.HEIGHT-1) {
				y = 0;
			}
			break;
		case LEFT:
			x--;
			if (x < 0) {
				x = Global.WIDTH-1;
			}
			break;
		case RIGHT:
			x++;
			if (x > Global.WIDTH-1) {
				x = 0;
			}
			break;
		}
		
		//2加头
		body.addFirst(new Point(x, y));
	}
	
	public void changeDirection(int direction) {
		System.out.println("Snake' chageDirection");
		newDirection = direction;
	}
	
	public void eatFood() {
		System.out.println("Snake's eatFood");
		//如果吃到食物，则将之前删去的尾巴重新添加到尾巴上
		body.addLast(oldTail);
	}
	
	public boolean isEatBody() {
		System.out.println("Snake's is EatFood");
		
		for(int i=1; i<body.size(); i++) {
			if (body.get(i).equals(this.getHead())) {
				return true;
			}
		}
			
		return false;
	}
	
	public void drawMe(Graphics g) {
		System.out.println("Snake's drawMe");
		g.setColor(Color.BLUE);
		for (Point p : body) {
			g.fill3DRect(p.x*Global.CELL_SIZE, p.y*Global.CELL_SIZE,Global.CELL_SIZE , Global.CELL_SIZE, true);
		}
		g.setColor(Color.RED);
		g.fill3DRect((int)this.getHead().getX()*Global.CELL_SIZE, (int)this.getHead().getY()*Global.CELL_SIZE, Global.CELL_SIZE , Global.CELL_SIZE, true);
	}
	
	private class SnakeDriver implements Runnable{

		@Override
		public void run() {
			while(life) {
				//走一步，会听一下。挨个执行自己所有的监听器方法
				move();
				for (SnakeListener snakeListener : listeners) {
						snakeListener.snakeMoved(Snake.this);
				}
				
				try {
					Thread.sleep(160);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//线程会调用run方法里面的内容
	public void start() {
		new Thread(new SnakeDriver()).start();
	}
	
	public void addSnakeListener(SnakeListener listener) {
		
		if (listener != null) {
			listeners.add(listener);
		}
	}
	
	//获取蛇头的方法
	public Point getHead() {
		return body.getFirst();
	}
	
	//蛇死掉
	public void die() {
		life = false;
	}
	
}
