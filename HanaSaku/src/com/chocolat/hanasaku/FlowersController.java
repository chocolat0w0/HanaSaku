package com.chocolat.hanasaku;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;


public class FlowersController {
	private static final int MAX_FLOWER_NUMBER = 10;
	
	private ArrayList<Object> flowersList;
	private RelativeLayout viewGroup;
	
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

	public void remove(Flower flower) {
		flowersList.remove(flower);
		viewGroup.removeView(flower);
	}

}
