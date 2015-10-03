package com.hackzurich.homecraft;

public final class Normalizer {
	
    private double fromMin;
    private double fromMax;
    private double toMin; 
    private double toMax;
	
	private Normalizer(double _fromMin, double _fromMax, double _toMin, double _toMax) {
		fromMin = _fromMin;
		fromMax = _fromMax;
		toMin = _toMin;
		toMax = _toMax;
	}
	
	public double Normalize(double value)
	{
		if (fromMax == fromMin) return 0.0;
		return toMin + (toMax - toMin) * ((value - fromMin) / (fromMax - fromMin));
	}
}
