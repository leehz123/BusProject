package park.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import hong.SaveInfo;
import hong.selectseat.SelectSeatMainFrame;

public class BeforePayButton extends JButton{
	
	public BeforePayButton(SaveInfo user) {
		super("Before");
		
		setSize(40, 40);
		setFont(new Font("�ü�ü", Font.BOLD,15));
		setForeground(new Color(0x000000));
		setBackground(new Color(0xffff00));		
	}
}
