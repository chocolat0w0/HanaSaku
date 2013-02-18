package com.chocolat.hanasaku;

import java.util.TimerTask;

import android.os.Handler;
import android.widget.RelativeLayout;

public class TimerController extends TimerTask {
	private Butterfly butterfly;
	private RelativeLayout viewGroup;
	private Handler mHandler;
	
	public TimerController(Butterfly butterfly, RelativeLayout viewGroup) {
		super();
		this.butterfly = butterfly;
		this.viewGroup = viewGroup;
		this.mHandler = new Handler();
	}

	@Override
	public void run() {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				butterfly.flyToNearestFlower();
				viewGroup.invalidate();
				
			}
		});
	}

}
