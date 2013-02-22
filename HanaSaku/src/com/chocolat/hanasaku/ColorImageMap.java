package com.chocolat.hanasaku;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;


public class ColorImageMap {
	public static enum ColorList {
		RED, GREEN, BLUE,
	}
	public static final Map<ColorList, Integer> BUTTERFLY_IMAGE_MAP;
	public static final Map<ColorList, Integer> FLOWER_IMAGE_MAP;
	private static final ColorList[] colors = ColorList.class.getEnumConstants();
	
	static {
		Map<ColorList, Integer> aMap = new EnumMap<ColorList, Integer>(ColorList.class);
		aMap.put(ColorList.RED, R.drawable.butterfly_red);
		aMap.put(ColorList.GREEN, R.drawable.butterfly_green);
		aMap.put(ColorList.BLUE, R.drawable.butterfly_blue);
		BUTTERFLY_IMAGE_MAP = Collections.unmodifiableMap(aMap);

		Map<ColorList, Integer> bMap = new EnumMap<ColorList, Integer>(ColorList.class);
		bMap.put(ColorList.RED, R.drawable.flower_red);
		bMap.put(ColorList.GREEN, R.drawable.flower_green);
		bMap.put(ColorList.BLUE, R.drawable.flower_blue);
		FLOWER_IMAGE_MAP = Collections.unmodifiableMap(bMap);
	}
	
	public static ColorList random() {
		Random random = new Random();
		return colors[random.nextInt(colors.length)];
	}
}
