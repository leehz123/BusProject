package lee.mpcomponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MPreservationlistPanel extends JPanel {
	
	//�� Ŭ������ ���Ź�ȣ �����ϴ� ���� �ϳ� �����߰��� �׷��� üũ�ڽ� ������ �� � ���Ź�ȣ ������ ���� �� �� ���� �� ����
	int br_id;//���Ź�ȣ
	String reservationdetailStr;
	
	public MPreservationlistPanel(Integer br_id, String reservationdetailStr) {
		
		this.br_id = br_id;//���Ź�ȣ �޴°� ����!
		this.reservationdetailStr = reservationdetailStr;
		
		
		JLabel MPreservationdetailLb = new JLabel(reservationdetailStr);
		
		//�������� �ߴ� �� ����
		MPreservationdetailLb.setBounds(20, 0, 300, 150);
		MPreservationdetailLb.setFont(new Font("���", Font.BOLD, 14));
		add(MPreservationdetailLb);		

		setBackground(Color.WHITE);
		setLayout(null);
		setOpaque(true);  
		setSize(470, 180);
	}
}
