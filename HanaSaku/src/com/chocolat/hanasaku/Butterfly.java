package com.chocolat.hanasaku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Butterfly extends View{

	private static final float INITIAL_IMAGEX = 30;
	private static final float INITIAL_IMAGEY = 30;
	
	private FlowersController flowersController;
	private float imageX = INITIAL_IMAGEX;
	private float imageY = INITIAL_IMAGEY;
	private int color;
	private boolean isCatched = false;
	private Bitmap mBitmap;
	private Paint mPaint;
	
	public Butterfly(Context context, FlowersController flowersController) {
		super(context);
		this.flowersController = flowersController;
		this.mPaint = new Paint();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly);
		canvas.drawBitmap(mBitmap, imageX, imageY, mPaint);
	}

	public void setStatusToCatched() {
		this.isCatched = true;
	}

	public void setStatusToUncatched() {
		this.isCatched = false;
	}

	public void moveByDrag(MotionEvent event) {
		if (isCatched) {
			this.imageX = event.getX() - mBitmap.getWidth() / 2;
			this.imageY = event.getY() - mBitmap.getHeight() / 2;
			this.invalidate();
		}
	}

	// TODO: if文が汚い・・
	public boolean isExisted(MotionEvent event) {
		if (imageX <= event.getX() && event.getX() <= (imageX + mBitmap.getWidth())
				&& imageY <= event.getY() && event.getY() <=(imageY + mBitmap.getHeight())) {
			return true;
		}
		return false;
	}

	public void flyToNearestFlower() {
		if (!isCatched) {
			Flower nextFlower = flowersController.searchNearest(imageX, imageY);
			if (nextFlower == null) {
				return;
			}
			setNextFramePosition(nextFlower);
		}
	}

	// TODO: 移動ロジック（速度調整）は仮
	private void setNextFramePosition(Flower nextFlower) {
//		imageX += (nextFlower.get - imageX) / 5;
		imageX += 5;
		imageY += 5;
	}

	
}
