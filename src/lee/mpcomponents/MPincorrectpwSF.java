package lee.mpcomponents;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lee.RoundedButton;

public class MPincorrectpwSF extends JFrame implements ActionListener{
	RoundedButton closeBtn = new RoundedButton("�ݱ�");	
	
	public MPincorrectpwSF() {
		super("�߸��� ��й�ȣ");
		
		JLabel label = new JLabel("<html><pre>�߸��� ��й�ȣ�Դϴ�.<br> �ٽ� �Է����ּ���.</pre></html>");

		// �� ����
		label.setBounds(110, 60, 300, 30);
		add(label);
		
		//�ݱ� ��ư ����
		closeBtn.setBounds(125, 130, 90, 35);
		closeBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		closeBtn.addActionListener(this);
		add(closeBtn);	
		
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setSize(350,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}	
}
