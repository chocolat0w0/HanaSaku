package com.chocolat.hanasaku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Butterfly extends View{

	private static final float INITIAL_IMAGEX = 30;
	private static final float INITIAL_IMAGEY = 30;
	private static final float OVERLAP_AREA = 15;
	
	private FlowersController flowersController;
	private float imageCenterX = INITIAL_IMAGEX;
	private float imageCenterY = INITIAL_IMAGEY;
	private float imageLeftX = 0;
	private float imageRightX = 0;
	private float imageTopY = 0;
	private float imageBottomY = 0;
	private int color = 0;
	private boolean isCatched = false;
	private Bitmap mBitmap;
	private Paint mPaint;
	
	@SuppressLint("DrawAllocation")
	public Butterfly(Context context, FlowersController flowersController) {
		super(context);
		this.flowersController = flowersController;
		this.mPaint = new Paint();
		mBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.butterfly);
		refreshImageSideXY(imageCenterX, imageCenterY);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap,
				imageLeftX,
				imageTopY,
				mPaint);
	}

	public void setStatusToCatched() {
		this.isCatched = true;
	}

	public void setStatusToUncatched() {
		this.isCatched = false;
	}

	public void moveByDrag(MotionEvent event) {
		if (isCatched) {
			this.imageCenterX = event.getX();
			this.imageCenterY = event.getY();
			refreshImageSideXY(imageCenterX, imageCenterY);
			this.invalidate();
		}
	}

	private void refreshImageSideXY(float imageCenterX, float imageCenterY) {
		imageLeftX = imageCenterX - mBitmap.getWidth() / 2;
		imageRightX = imageCenterX + mBitmap.getWidth() / 2;
		imageTopY = imageCenterY - mBitmap.getHeight() / 2;
		imageBottomY = imageCenterY + mBitmap.getHeight() / 2;
	}

	// TODO: if文が汚い・・
	public boolean isExisted(MotionEvent event) {
		if (imageLeftX <= event.getX() && event.getX() <= imageRightX
				&& imageTopY <= event.getY() && event.getY() <= imageBottomY) {
			return true;
		}
		return false;
	}

	public void flyToNearestFlower() {
		if (!this.isCatched) {
			Flower nextFlower =
					flowersController
					.searchNearest(imageCenterX, imageCenterY);
			if (nextFlower == null) {
				return;
			}
			setNextFramePosition(nextFlower);

			if(nextFlower.calcDistance(imageCenterX, imageCenterY)
					< OVERLAP_AREA) {
				// TODO: 色変更
				flowersController.remove(nextFlower);
			}
		}
	}

	// TODO: 移動ロジック（速度調整）は仮
	private void setNextFramePosition(Flower nextFlower) {
		imageCenterX += (nextFlower.getImageX() - imageCenterX) / 5;
		imageCenterY += (nextFlower.getImageY() - imageCenterY) / 5;
		refreshImageSideXY(imageCenterX, imageCenterY);
	}

	
}
