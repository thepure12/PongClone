package com.tp12life.pongclone;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle
{
	static enum LOR
	{
		LEFT, RIGHT
	}

	static final float SPEED = 5f;
	static float WIDTH = .5f;
	static float HEIGHT = 3f;

	private Vector2 position = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();

	public Paddle(float gridSize, LOR lor)
	{
		if (lor == LOR.LEFT)
			this.position = new Vector2(-1 * (gridSize / 2 - .05f),
					0 - HEIGHT / 2);
		else
			this.position = new Vector2(gridSize / 2 - WIDTH - .05f,
					0 - HEIGHT / 2);
		bounds.width = WIDTH;
		bounds.height = HEIGHT;
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public float getWidth()
	{
		return bounds.width;
	}

	public float getHeight()
	{
		return bounds.height;
	}

	public float getPosX()
	{
		return position.x;
	}

	public float getPosY()
	{
		return position.y;
	}

	public Vector2 getVelocity()
	{
		return velocity;
	}

	public float getSpeed()
	{
		return SPEED;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}

	public void update()
	{
		bounds.height = HEIGHT;
		bounds.width = WIDTH;
		bounds.x = position.x;
		bounds.y = position.y;
	}
}
