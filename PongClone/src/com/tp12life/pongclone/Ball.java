package com.tp12life.pongclone;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball
{
	static float SPEED = 4f;
	static float SIZE = .3f;
	
	private Vector2 position = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private Circle bounds = new Circle();
	
	public Ball(Vector2 position)
	{
		this.position = position;
		bounds.radius = SIZE;
	}
	
	public float getRadius()
	{
		return bounds.radius;
	}
	
	public Circle getBounds()
	{
		return bounds;
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
		bounds.x = position.x;
		bounds.y = position.y;
		bounds.radius = SIZE;
	}
}
