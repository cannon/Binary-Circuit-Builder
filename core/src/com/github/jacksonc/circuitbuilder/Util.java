package com.github.jacksonc.circuitbuilder;

public class Util {
	public static boolean gateOutput(Gate g) {
		if(g == null) {
			return false;
		} else {
			return g.getOutput();
		}
	}
}
