package com.chocolat.hanasaku;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;


public class ColorImageMap {
	public static enum ColorList {
		RED, GREEN, BLUE,
	}
	public static final Map<ColorList, Integer> butterflyImageMap;
	public static final Map<ColorList, Integer> flowerImageMap;
	
	static {
		Map<ColorList, Integer> aMap = new EnumMap<ColorList, Integer>(ColorList.class);
		aMap.put(ColorList.RED, R.drawable.butterfly_red);
		aMap.put(ColorList.GREEN, R.drawable.butterfly_green);
		aMap.put(ColorList.BLUE, R.drawable.butterfly_blue);
		butterflyImageMap = Collections.unmodifiableMap(aMap);

		Map<ColorList, Integer> bMap = new EnumMap<ColorList, Integer>(ColorList.class);
		bMap.put(ColorList.RED, R.drawable.flower_red);
		bMap.put(ColorList.GREEN, R.drawable.flower_green);
		bMap.put(ColorList.BLUE, R.drawable.flower_blue);
		flowerImageMap = Collections.unmodifiableMap(bMap);
	}
}
