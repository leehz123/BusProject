package lee.mpcomponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import lee.RoundedButton;

public class MPleavePanel extends JPanel {
	
	
	JLabel MPleaveConfirmLb = new JLabel("���� Ż�� ���Ͻø� ��й�ȣ�� �Է����ּ���.");
	RoundedButton MPleaveYesBtn = new RoundedButton("Ȯ��");
	JPasswordField MPleavePf = new JPasswordField();
	
	public MPleavePanel() {
		
		setLayout(null);
		
		//Ż���Ͻðڽ��ϱ�? �� ����
		MPleaveConfirmLb.setBounds(50, 30, 400, 60);
		MPleaveConfirmLb.setFont(new Font("���", Font.BOLD, 15));
		add(MPleaveConfirmLb);

		JLabel MPleavePwLb = new JLabel();
		MPleavePwLb.setText("��й�ȣ ");
		MPleavePwLb.setBounds(50, 100, 100, 27);
		MPleavePwLb.setFont(new Font("���", Font.BOLD, 15));
		add(MPleavePwLb);
		
		//��й�ȣ �ؽ�Ʈ�ʵ� ����
		MPleavePf.setBounds(120, 100, 250, 27);
		MPleavePf.setEchoChar('��');
		MPleavePf.setBackground(new Color(219,219,219));
		MPleavePf.setBorder(null);
		add(MPleavePf);
		
		//Ȯ�� ��ư ����
		MPleaveYesBtn.setBounds(380, 170, 90, 35);
		MPleaveYesBtn.setBorderPainted(false);
		MPleaveYesBtn.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		add(MPleaveYesBtn);
		
		setBackground(Color.WHITE);
	}


	//��й�ȣ �ؽ�Ʈ ��� �޼���
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
