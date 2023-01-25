package lee.mpcomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lee.RoundedButton;

public class MPrealSF extends JFrame implements ActionListener{
	
	RoundedButton yesBtn = new RoundedButton("��");
	RoundedButton noBtn = new RoundedButton("�ƴϿ�");
	
	public MPrealSF(String title, String msg, int x, int y, int width, int height) {
		super(title);
		
		JLabel label = new JLabel(msg);
		label.setBounds(x, y, width, height);
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
		getContentPane().setBackground(Color.WHITE);
	}

	//�ƴϿ���ư �׼�
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
