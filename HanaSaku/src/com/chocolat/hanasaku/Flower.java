package com.chocolat.hanasaku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("ViewConstructor")
public class Flower extends View implements ColorInterface {
	private float imageX = 0;
	private float imageY = 0;
	private Paint mPaint;
	private Bitmap mBitmap;
	private int resorceID;

	public Flower(Context context, MotionEvent event, int resorceID) {
		super(context);
		mPaint = new Paint();
		setPosition(event);
		this.resorceID = resorceID;
		setImage(resorceID);
	}
	
	private void setImage(int resorceID) {
		mBitmap = BitmapFactory.decodeResource(getResources(), resorceID);		
	}

	public float getImageX() {
		return this.imageX;
	}

	public float getImageY() {
		return this.imageY;
	}
	
	public int getColor() {
		return this.resorceID;
	}
	
	private void setPosition(MotionEvent event) {
		this.imageX = event.getX();
		this.imageY = event.getY();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap,
				imageX - mBitmap.getWidth() / 2,
				imageY - mBitmap.getHeight() / 2,
				mPaint);
	}

	protected float calcDistance(float butterflyX, float butterflyY) {
		return (float) Math.sqrt(Math.pow(imageX-butterflyX, 2)
				+ Math.pow(imageY-butterflyY, 2));
		
	}


}
