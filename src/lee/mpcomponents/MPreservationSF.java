package lee.mpcomponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MPreservationSF extends JFrame implements ActionListener {
	
	JButton yesBtn = new JButton("��");
	
	public MPreservationSF() {
		super("�������");
		
		JLabel label = new JLabel("���� ����Ͻðڽ��ϱ�?");
		JButton noBtn = new JButton("�ƴϿ�");
			
		
		//�������? �� ����
		label.setBounds(100, 60, 300, 30);
		add(label);
		
		//�� ��ư ����
		yesBtn.setBounds(60, 130, 80, 30);
		add(yesBtn);
		
		//�ƴϿ� ��ư ����
		noBtn.setBounds(190, 130, 80, 30);
		add(noBtn);
		noBtn.addActionListener(this); //�ƴϿ� ��ư �׼��� ���⼭ �ٷ� ����
		
		
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
