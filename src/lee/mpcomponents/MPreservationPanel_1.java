package lee.mpcomponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

//���ų��� ���� �� �г�
public class MPreservationPanel_1 extends JPanel {

	
	JLabel MPnoreservationLb = new JLabel("���� ���Ÿų����� �������� �ʽ��ϴ�.");
	
	public MPreservationPanel_1() {
		
		setLayout(null);
		
		
		MPnoreservationLb.setBounds(30, 30, 300, 30);
		MPnoreservationLb.setFont(new Font("���", Font.BOLD, 15));
		add(MPnoreservationLb);
		
		setBackground(Color.WHITE);
	}
	
	
}
