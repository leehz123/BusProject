package lee.mpcomponents;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lee.RoundedButton;

public class MPleaveRealSF extends JFrame implements ActionListener{
	
	RoundedButton yesBtn = new RoundedButton("��");
	RoundedButton noBtn = new RoundedButton("�ƴϿ�");
	
	public MPleaveRealSF() {
		
		JLabel label = new JLabel("���� Ż���Ͻðڽ��ϱ�?");
		label.setBounds(100, 60, 300, 30);
		add(label);
		
		yesBtn.setBounds(60, 120, 90, 35);
		yesBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		add(yesBtn);
		
		//�ƴϿ� ��ư ����
		noBtn.setBounds(180, 120, 90, 35);
		noBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		add(noBtn);
		noBtn.addActionListener(this); //�ƴϿ� ��ư �׼��� ���⼭ �ٷ� ����
		
		
		setTitle("���� Ż��");
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setSize(350,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}

	//�ƴϿ���ư �׼�
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
