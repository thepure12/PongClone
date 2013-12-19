package com.tp12life.pongclone;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.tp12life.pongclone.Paddle.LOR;

public class PongClone implements ApplicationListener, InputProcessor
{
	private final int GRID_SIZE = 10;
	private OrthographicCamera camera;
	private ShapeRenderer renderer;
	private Ball ball;
	private Paddle paddle1, paddle2;
	private boolean w, s, up, down, lpmoving, rpmoving;
	private float delta;
	private float paddleCollisionDelay;

	@Override
	public void create()
	{
		Gdx.input.setInputProcessor(this);
		camera = new OrthographicCamera(GRID_SIZE, GRID_SIZE);
		camera.position.set(0, 0, 0);
		renderer = new ShapeRenderer();
		ball = new Ball(new Vector2(0, 0));
		paddle1 = new Paddle(GRID_SIZE, LOR.LEFT);
		paddle2 = new Paddle(GRID_SIZE, LOR.RIGHT);
		ball.getVelocity().x = -ball.getSpeed();
		ball.getVelocity().y = -ball.getSpeed() / 2;
		updatePositions();
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		delta = Gdx.graphics.getDeltaTime();
		proccessInput();
		updatePositions();
		checkCollision();
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Filled);
			Circle circ = ball.getBounds();
			float x = ball.getPosX();
			float y = ball.getPosY();
			renderer.setColor(new Color(1, 0, 0, 1));
			renderer.circle(x, y, circ.radius, 1665);
			
			Rectangle rect = paddle1.getBounds();
			x = paddle1.getPosX();
			y = paddle1.getPosY();
			renderer.setColor(new Color(0, 0, 1, 1));
			renderer.rect(x, y, rect.width, rect.height);
			
			rect = paddle2.getBounds();
			x = paddle2.getPosX();
			y = paddle2.getPosY();
			renderer.setColor(new Color(0, 0, 1, 1));
			renderer.rect(x, y, rect.width, rect.height);
		renderer.end();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	public void proccessInput()
	{
		if (w)
		{
			paddle1.getVelocity().y = paddle1.getSpeed();
		}
		if (s)
		{
			paddle1.getVelocity().y = -paddle1.getSpeed();
		}
		if (up)
		{
			paddle2.getVelocity().y = paddle2.getSpeed();
		}
		if (down)
		{
			paddle2.getVelocity().y = -paddle2.getSpeed();
		}
	}
	
	public void updatePositions()
	{
		ball.getPosition().add(ball.getVelocity().cpy().scl(delta));
		if (lpmoving)
			paddle1.getPosition().add(paddle1.getVelocity().cpy().scl(delta));
		if (rpmoving)
			paddle2.getPosition().add(paddle2.getVelocity().cpy().scl(delta));
		ball.update();
		paddle1.update();
		paddle2.update();
	}
	
	public void checkCollision()
	{
		Rectangle ballAsRect = new Rectangle(ball.getPosX() - ball.getRadius(), ball.getPosY() - ball.getRadius(), ball.getRadius() * 2, ball.getRadius() * 2);
		paddleCollisionDelay -= delta;
		if (paddleCollisionDelay <= 0)
			if (paddle1.getBounds().overlaps(ballAsRect) || paddle2.getBounds().overlaps(ballAsRect))
			{
				ball.getVelocity().x *= -1;
				paddleCollisionDelay = .5f;
			}
		if (ballAsRect.getY() > GRID_SIZE / 2 - ballAsRect.height || ballAsRect.getY() < -GRID_SIZE / 2)
		{
			ball.getVelocity().y *= -1;
		}
		if (ballAsRect.getX() > GRID_SIZE / 2 || ballAsRect.getX() < -GRID_SIZE / 2)
		{
			ball.getPosition().x = 0;
			ball.getPosition().y = 0;
		}
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.W)
		{
			lpmoving = true;
			w = true;
		}
		if (keycode == Keys.S)
		{
			lpmoving = true;
			s = true;
		}
		if (keycode == Keys.UP)
		{
			rpmoving = true;
			up = true;
		}
		if (keycode == Keys.DOWN)
		{
			rpmoving = true;
			down = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == Keys.W)
		{
			lpmoving = false;
			w = false;
		}
		if (keycode == Keys.S)
		{
			lpmoving = false;
			s = false;
		}
		if (keycode == Keys.UP)
		{
			rpmoving = false;
			up = false;
		}
		if (keycode == Keys.DOWN)
		{
			rpmoving = false;
			down = false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
