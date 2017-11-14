package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseMotionListener, MouseListener {
	private View view;
	private Point p1;
	private Point p2;
	private boolean resize = false ;
	private int resize_corner = 0 ;
	private boolean move = false;

	@Override
	protected void paintComponent(Graphics g) {
		//Graphics2D canvas = (Graphics2D) g;
		//canvas.setStroke(new BasicStroke(stroke));
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		view.getModel().refresh(g);
	}

	public Canvas(View view) {
		super();
		this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
		this.view = view;
		setBackground(new java.awt.Color(255, 255, 255));
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (view.getActiveBtns() == 1) {
			p1 = e.getPoint();
			view.getController().draw(p1, p1);
		}
		else {
			if(isWithinCorner(e.getPoint())) {
				p1 = e.getPoint() ;
				resize = true ;
			}

			//if inside the shape
			if(isInsideShape(e.getPoint())){
				p1 = e.getPoint();
				move = true;
				System.out.println("Pressed");
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (view.getActiveBtns() == 1) {
			p2 = e.getPoint();
			view.getController().dragDraw(p1, p2);
			repaint();
		}
		else if (resize) {
			p2 = e.getPoint();
			view.getController().resizeSelected(p1 , p2 , resize_corner);
			p1 = new Point(p2);
			repaint();
		}
		else if (move) {
			p2 = e.getPoint();
			// TODO Move Stuff
			view.getController().moveSelected(p1, p2);
			p1 = new Point(p2);
			repaint();
			System.out.println("Dragged");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (view.getActiveBtns() == 1) {
			p2 = e.getPoint();
			if (p1.equals(p2)) {
				view.getController().removeCurrentDraw();
			} else {
				view.getController().removeCurrentDraw();
				view.getController().draw(p1, p2);
			}
			repaint();
		}
		else {
			if(resize) {
				resize = false ;
			}
			if(move) {
				move = false;
				System.out.println("Released");
			}
		}
	}

	private boolean isWithinCorner(Point point) {
		Point []  bonds = view.getModel().getFull_bonds();
		if(bonds[0] == null)
			return false;
		else {
			int size = view.getModel().getSize_corner();
			for(int i = 0 ; i < 4 ; i++) {
				Point x = bonds[i];
				if((point.x <= x.x + size && point.x >= x.x - size) && (point.y <= x.y + size && point.y >= x.y - size)) {
					resize_corner = i+1 ;
					return true ;
				}
			}
		}
		return false ;
	}

	private boolean isInsideShape(Point point){
		Point []  bonds = view.getModel().getFull_bonds();
		if(bonds[0] == null)
			return false;
		else {
			int minX = bonds[0].x, maxX = bonds[0].x, minY = bonds[0].y, maxY = bonds[0].y;
			for(int i = 0 ; i < 4 ; i++) {
				Point p = bonds[i];
				minX = Math.min(minX, p.x);
				maxX = Math.max(maxX, p.x);
				minY = Math.min(minY, p.y);
				maxY = Math.max(maxY, p.y);
			}
			if(point.x >= minX && point.x <= maxX && point.y >= minY && point.y <= maxY){
				return true;
			}
		}
		return false ;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return super.getGraphics();
	}

}
