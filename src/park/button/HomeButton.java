package park.button;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import hong.SaveInfo;

public class HomeButton extends JButton{ // Ȩ ȭ������ ���� ��ư

	public HomeButton(ImageIcon imageicon) {
		super(imageicon);
					
		setBounds(30,10,40,40);
		setFont(new Font("�޸� ����ü", Font.BOLD,15));
		setBorderPainted(false);
		setOpaque(false);
		setFocusPainted(false);
		setForeground(new Color(0x000000));
		setBackground(new Color(0xffff00));
	}
}
