package lee.mpcomponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MPpreventnulltfSF extends JFrame implements ActionListener {
	
	JButton closeBtn = new JButton("�ݱ�");
	
	public MPpreventnulltfSF() {
			
		super("�� ĭ �Է�");
		
		JLabel label = new JLabel("�� ĭ�� ��� �Է����ּ���.");
		
			
		// �� ����
		label.setBounds(95, 60, 300, 30);
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
