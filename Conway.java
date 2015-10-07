import javax.swing.*;
import java.awt.*;

public class Conway {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Conway's Game of Life");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		LifeGUI life = new LifeGUI();

		frame.add(life);

		frame.pack();

		Dimension lifeSize = life.getSize();
		Insets frameInsets = frame.getInsets();
		lifeSize.setSize(lifeSize.getWidth() +
		                 frameInsets.left +
						 frameInsets.right,
						 lifeSize.getHeight() +
						 frameInsets.top +
						 frameInsets.bottom);

		frame.setMinimumSize(lifeSize);
		frame.setResizable(false);

		frame.setVisible(true);
	}
}
