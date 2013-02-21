package com.chocolat.hanasaku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("ViewConstructor")
public class Flower extends View {
	private float imageX = 0;
	private float imageY = 0;
	private Paint mPaint;
	private Bitmap mBitmap;
	private int color;

	public Flower(Context context, MotionEvent event) {
		super(context);
		mPaint = new Paint(Color.WHITE);
		setPosition(event);
		setRandomColor();
	}
	
	protected float getImageX() {
		return this.imageX;
	}

	protected float getImageY() {
		return this.imageY;
	}
	
	protected int getColor() {
		return this.color;
	}
	
	private void setRandomColor() {
		// TODO 自動生成されたメソッド・スタブ
		color = (int)(Math.random() * 3);
		int resorce = 0;
		// TODO: 色数が増えても対応できるようにする
		// TODO: 色と画像のmappingを別に持つようにする
		switch (color) {
		case 0:
			resorce = R.drawable.flower_red;
			break;
		case 1:
			resorce = R.drawable.flower_green;
			break;
		case 2:
			resorce = R.drawable.flower_blue;
			break;
		default:
			break;
		}
		mBitmap = BitmapFactory.decodeResource(getResources(), resorce);
	}
	
	protected void changeButterflyColor(Butterfly butterfly) {
		int resorce = 0;
		switch (color) {
		case 0:
			resorce = R.drawable.butterfly_red;
			break;
		case 1:
			resorce = R.drawable.butterfly_green;
			break;
		case 2:
			resorce = R.drawable.butterfly_blue;
			break;
		default:
			break;
		}
		butterfly.setColor(resorce);
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
