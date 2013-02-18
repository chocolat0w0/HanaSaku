package com.chocolat.hanasaku;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private RelativeLayout viewGroup;
	private Butterfly butterfly;
	private FlowersController flowersController;
	private Timer mTimer;
	private TimerController timerController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		flowersController = new FlowersController(viewGroup);
		butterfly = new Butterfly(this, flowersController);
		viewGroup.addView(butterfly);
		setContentView(viewGroup);
		mTimer = new Timer(true);
		timerController = new TimerController(butterfly, viewGroup);
		mTimer.schedule(timerController, 100, 100);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//TODO: 判定ロジックが汚い・・
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (butterfly.isExisted(event)) {
					butterfly.setStatusToCatched();
			}
			else {
				flowersController.add(this, event);
			}
		}
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			butterfly.moveByDrag(event);
		}
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			butterfly.setStatusToUncatched();
		}
		return true;
	}
}
