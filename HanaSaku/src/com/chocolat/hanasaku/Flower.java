package com.chocolat.hanasaku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Flower extends View {
	private float imageX = 0;
	private float imageY = 0;
	private int color = 0;
	private Paint mPaint;
	private Bitmap mBitmap;

	public Flower(Context context, MotionEvent event) {
		super(context);
		mPaint = new Paint();
		setPosition(event);
		setColor();
		setImage();
	}

	private void setImage() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	private void setColor() {
		// TODO 自動生成されたメソッド・スタブ
		this.color = 0;
		
	}

	private void setPosition(MotionEvent event) {
		this.imageX = event.getX();
		this.imageY = event.getY();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dummy_flower);
		canvas.drawBitmap(mBitmap, imageX - mBitmap.getWidth() / 2, imageY - mBitmap.getHeight() / 2, mPaint);
	}

	public float calcDistance(float butterflyX, float butterflyY) {
		return (float) Math.sqrt(Math.pow(imageX-butterflyX, 2) + Math.pow(imageY-butterflyY, 2));
		
	}

}
