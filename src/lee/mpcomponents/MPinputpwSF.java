package lee.mpcomponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import lee.RoundedButton;

public class MPinputpwSF extends JFrame {
	RoundedButton completeBtn = new RoundedButton("�Է�");	
	JPasswordField inputpwPf = new JPasswordField(25);
	
	Boolean onOff = false;
	
	public MPinputpwSF() {
		super("��й�ȣ �Է�");
		onOff = true;
		
		JLabel label = new JLabel("��й�ȣ�� �Է����ּ���.");

		// �� ����
		label.setBounds(90, 40, 300, 30);
		label.setFont(new Font("���", Font.BOLD, 13));
		add(label);
		
		//�Է� ��ư ����
		completeBtn.setBounds(125, 130, 90, 35);
		completeBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		
		add(completeBtn);	
		
		inputpwPf.setBounds(85, 80, 170, 30);
		inputpwPf.setEchoChar('��');
		inputpwPf.setBackground(new Color(219,219,219));
		inputpwPf.setBorder(null);
		add(inputpwPf);
		
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setSize(350,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
	}

	

	
	
	public String MPgetPwd(JPasswordField MPnewpwTf) {
		StringBuilder pw = new StringBuilder();	
		char[] secret_pw = MPnewpwTf.getPassword(); 

	    for(char cha : secret_pw){         
	        Character.toString(cha); 
	        pw.append(cha);
	    }
		return pw.toString();
	}
}
