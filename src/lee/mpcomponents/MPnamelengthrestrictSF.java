package lee.mpcomponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MPnamelengthrestrictSF extends JFrame implements ActionListener{

	public MPnamelengthrestrictSF() {
		
		super("�Է� ����");
		JButton closeBtn = new JButton("�ݱ�");
		
		JLabel label = new JLabel("�̸��� ��й�ȣ�� �ִ� 10���ڱ��� �Է� �����մϴ�.");
		

		
		// �� ����
		label.setBounds(25, 60, 300, 30);
		add(label);
		
		//�ݱ� ��ư ����
		closeBtn.setBounds(125, 130, 80, 30);
		
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
