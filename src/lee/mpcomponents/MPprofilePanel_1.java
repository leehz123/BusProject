package lee.mpcomponents;

import java.awt.Font;

import javax.swing.JPanel;

import lee.RoundedButton;

public class MPprofilePanel_1 extends JPanel {
	
	RoundedButton MPeditBtn = new RoundedButton("�����ϱ�");
	
	public MPprofilePanel_1() {

		setLayout(null);
		
		//�����ϱ� ��ư ����
		MPeditBtn.setBounds(350, 200, 90, 35);
		MPeditBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		
		add(MPeditBtn);		
	}
	
}
