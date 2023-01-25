package hong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class NextButton extends JButton {
	
	//private LineBorder lb = new LineBorder(Color.GRAY, 3);
	private Dimension dimension = new Dimension(100,40);
	
	public NextButton() {
		
		super("Ȯ��");
		setOpaque(false);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setFont(new Font("�޸�����ü", Font.BOLD, 17));
		setBorderPainted(false);
		//setBorder(lb);
		setPreferredSize(dimension);
	}

}
