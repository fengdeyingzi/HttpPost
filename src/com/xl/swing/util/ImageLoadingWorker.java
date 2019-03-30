package com.xl.swing.util;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

class ImageLoadingWorker extends SwingWorker<ArrayList<ImageIcon>, String> {
	 private JTextArea log;
	 private JPanel viewer;
	 private String[] filenames;

	 public ImageLoadingWorker(JTextArea log, JPanel viewer, String... filenames) {
	  this.log = log;
	  this.viewer = viewer;
	  viewer.setLayout(new GridLayout(0,2,5,5));
	  this.filenames = filenames;
	 }

	 //在这里获取任务结果
	 @Override
	 protected void done() {
	  System.out.println("done()");
	  
	  viewer.removeAll();
	  try {
	   for (ImageIcon image : get()) {
	    JLabel jl = new JLabel(image);
	    viewer.add(jl);
	    viewer.revalidate();
	   }
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	 }

	 @Override
	 protected void process(List<String> messages) {
	  System.out.println("process()");

	  for (String message : messages) {
	   log.append(message);
	   log.append("\n");
	  }
	 }

	 @Override
	 protected ArrayList<ImageIcon> doInBackground() {
	  System.out.println("doInBackground()");
	  
	  ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	  int width = 400;
	  int height = 400;
	  for (String filename : filenames) {
	   ImageIcon ii = new ImageIcon();

	   try {
	    if(filename.startsWith("http")){
	     ii.setImage(new ImageIcon(new URL(filename)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    }else{
	     ii.setImage(new ImageIcon(ImageIO.read(new File(filename))).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    }
	    images.add(ii);
	    publish("Loaded " + filename);
	   } catch (IOException ioe) {
	    publish("Error loading " + filename);
	   }
	  }
	  return images;
	 }

	
	}