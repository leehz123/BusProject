package park.button;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import hong.SaveInfo;
import park.database.LoadRVID;
import park.database.SaveDB;
import park.frame.PayInfo;

public class PayButton extends JButton{

	public PayButton(SaveInfo user) {
		super("���� �ϱ�");
		setBackground(Color.cyan);
		setFont(new Font("�޸� ����ü", Font.BOLD,15));
		setBounds(190, 500, 100, 50);
	}
}
