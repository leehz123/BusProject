package park.combobox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import park.ReservationInfo;

public class SelectPayWayBox extends JComboBox{ // ���� ��� ������ �޺��ڽ�
	
	ReservationInfo user;
	public SelectPayWayBox(ReservationInfo user) {
		
		this.user = user;
		
		addItem("���� ���");
		addItem("------");
		addItem("�ſ�ī��");
		addItem("�Ｚ����");
		addItem("������");
		
		selectPayWay();
		setBackground(Color.white);
		setBounds(190, 440, 100, 30);
	}

	void selectPayWay() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String way = (String) getSelectedItem().toString();
				if(way.equals("------")||way.equals("���� ���")) {// Ű ���� �����ų� ---- ������ �ٽ� �����ϰ�
					new JOptionPane().showMessageDialog(null, "���� ����� �����ϼ���.","����",JOptionPane.ERROR_MESSAGE); 
				}else {
					user.setPayWay((String) getSelectedItem().toString()) ;
					System.out.println(user.getPayWay() + "���� ���� ���� �Ϸ�");
				}
			}
		});
	}
}