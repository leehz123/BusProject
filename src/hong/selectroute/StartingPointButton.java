package hong.selectroute;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class StartingPointButton extends JButton {
	
	//private LineBorder lb = new LineBorder(Color.GRAY, 3);
	private Dimension dimension = new Dimension(100,80);
	
	public StartingPointButton() {
		
		
		super("����");
		setOpaque(false);
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setFont(new Font("�޸�����ü", Font.BOLD, 25));
		//setBorder(lb);
		setBorderPainted(false);
		setPreferredSize(dimension);
	}
	
	public void setTextStpBtn(String str) {
		setText(str);
	}
}
