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
		Flower flower = null;
		if (MAX_FLOWER_NUMBER <= flowersList.size()) {
			return;
		}
		int random = (int)(Math.random() * 3);
		// TODO: 色数が増えても対応できるようにする
		// TODO: 色と画像のmappingを別に持つようにする
		switch (random) {
		case 0:
			flower = new Flower(context, event, ColorImageMap.ColorList.RED);
			break;
		case 1:
			flower = new Flower(context, event, ColorImageMap.ColorList.GREEN);
			break;
		case 2:
			flower = new Flower(context, event, ColorImageMap.ColorList.BLUE);
			break;
		default:
			break;
		}

		flowersList.add(flower);
		viewGroup.addView(flower);
		
		viewGroup.invalidate();
	}

	public Flower searchNearest(float butterflyX, float butterflyY) {
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
