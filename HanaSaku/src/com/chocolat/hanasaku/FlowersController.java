package com.chocolat.hanasaku;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class FlowersController {
	private static final int MAX_FLOWER_NUMBER = 10;
	
	private ArrayList<Object> flowersList;
	private RelativeLayout viewGroup;
	
	//TODO: 後で消す
	String eventX;
	String flowerX;
	int[] location = new int[2];

	public FlowersController(RelativeLayout viewGroup) {
		flowersList = new ArrayList<Object>();
		this.viewGroup = viewGroup;
	}

	@SuppressLint("NewApi")
	public void add(Context context, MotionEvent event) {
		if (flowersList.size() < MAX_FLOWER_NUMBER) {
			Flower flower = new Flower(context, event);
			flowersList.add(flower);
			viewGroup.addView(flower);
			// TODO: デバッグ用にデータ表示、後で消去すること
			eventX = Float.toString(event.getX());
			flower.getLocationOnScreen(location);
			flowerX = Float.toString(location[0]);
			Toast myToast = Toast.makeText(context, eventX + flowerX, Toast.LENGTH_SHORT);
			myToast.show();
			
			viewGroup.invalidate();
		}
	}

	public Flower searchNearest(float butterflyX, float butterflyY) {
		// TODO 自動生成されたメソッド・スタブ
		float distance = 1000;
		int nearestFlowerIndex = 0;
		if (flowersList.size() == 0) {
			return null;
		}
		for (int i = 0; i < flowersList.size(); i++) {
			final float aFlowerDistance = ((Flower) flowersList.get(i)).calcDistance(butterflyX, butterflyY);
			if(aFlowerDistance < distance) {
				distance = aFlowerDistance;
				nearestFlowerIndex = i;
			}
		}
		return (Flower)flowersList.get(nearestFlowerIndex);
	}

}
