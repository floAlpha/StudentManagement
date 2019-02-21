package sm;

import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JPanel {
	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = -613934653170337528L;
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}