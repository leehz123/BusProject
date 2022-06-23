package park.panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import park.ReservationInfo;
import park.combobox.SelectSeatAgeBox;

public class SelectSeatAgePanel extends JPanel {

	public SelectSeatAgePanel(ReservationInfo user) {
		LayoutManager manager = new FlowLayout(FlowLayout.CENTER,5,3);
		
		for(int i=0;i<user.getSeatName().size();i++) {// �¼�����ŭ �޺��ڽ��� �����Ѵ�
			add(new SelectSeatAgeBox(user,i));
		}
		setLayout(manager);
		setBackground(Color.white);
		setBounds(10,300,450,100);
	}
}