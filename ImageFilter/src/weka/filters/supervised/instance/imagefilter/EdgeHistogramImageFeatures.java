package weka.filters.supervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.EdgeHistogram;


public class EdgeHistogramImageFeatures extends BasicImageFeatures {

	private static final long serialVersionUID = 7586082011977076812L;

	public String globalInfo() {
		return "A batch filter for extracting MPEG7 edge histogram features from images.";
	}
	
	protected int getNumFeatures(){
		return 80;
	}
	protected double[] getFeatures(BufferedImage img){
		EdgeHistogram features=new EdgeHistogram();
		features.extract(img);
		return features.getDoubleHistogram();
	}
	
	public static void main(String[] args) {
		runFilter(new EdgeHistogramImageFeatures(), args);
	}
	
}