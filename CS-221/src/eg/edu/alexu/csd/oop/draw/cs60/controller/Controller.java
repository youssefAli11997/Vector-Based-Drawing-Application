package eg.edu.alexu.csd.oop.draw.cs60.controller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.IController;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.ShapesFactory;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.view.CustomButton;
import eg.edu.alexu.csd.oop.draw.cs60.view.View;

public class Controller implements IController {
	private View view ;
	private DrawingEngine model ;
	private ArrayList<CustomButton> btnList ;
	private Shape currentDraw ;
	
	public Controller(DrawingEngine model) {
		 this.model = model ;
		 view = new View(this , model);
		 view.createView();
		 view.createControls();
	}
	
	public void getBtnList () {
		this.btnList = view.getBtnList();
	}
	public void draw(Point p1, Point p2) {
		currentDraw = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		currentDraw.setColor(Color.BLUE);
		currentDraw.setFillColor(Color.RED);
		model.addShape(currentDraw);
	}
	
	public void dragDraw(Point p1, Point p2) {
		Shape newCurrent = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		newCurrent.setColor(Color.BLUE);
		newCurrent.setFillColor(Color.RED);
		model.dragDrawShape(currentDraw, newCurrent);
		currentDraw = newCurrent ;
	}
	
	
	private String getCurrentActive() {
		getBtnList();
		String shape = null ;
		for(CustomButton x : btnList) {
			if(x.getState()) {
				shape = x.getText();
			}
		}
		return shape ;
	}

	public void undo() {
		model.undo();
		view.getCanvas().repaint();
	}

	public void redo() {
		model.redo();
		view.getCanvas().repaint();
		
	}
	
}
