package lee.mpcomponents;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import lee.RoundedButton;

public class MPleavecompleteSF extends JFrame {

	RoundedButton yesBtn = new RoundedButton("��");
	
	public MPleavecompleteSF() {
		super("Ż�� �Ϸ�");
		
		JLabel label = new JLabel("<html><pre>     Ż�� �Ϸ�.<br>Ȩȭ������ �̵��մϴ�.</pre></html>");
		
		//Ż��Ϸ� �� ����
		label.setBounds(80, 40, 300, 30);
		add(label);
		
		//�� ��ư ����
		yesBtn.setBounds(95, 90, 90, 35);
		yesBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		add(yesBtn);
		
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
