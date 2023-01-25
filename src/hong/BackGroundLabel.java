package hong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class BackGroundLabel extends JLabel {
	
	final private static Color BACK_COLOR = Color.WHITE;
	private LineBorder lb = new LineBorder(Color.BLACK);
	
	public BackGroundLabel(String text, int w, int h, int fontsize) {
		super(text);
		//setOpaque(true);
		//setBackground(BACK_COLOR);
		setHorizontalAlignment(LEFT);
		Dimension dimension = new Dimension(w, h);
		setPreferredSize(dimension);
		setFont(new Font("�޸�����ü", Font.BOLD, fontsize));
		setForeground(Color.BLACK);
		//setBorder(lb);
	}
	
}
