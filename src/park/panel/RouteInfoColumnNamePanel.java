package park.panel;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import park.label.RouteInfoColumnNameLabel;

public class RouteInfoColumnNamePanel extends JPanel {// ��ũ�������� �� ���κп� �� �¼����� �÷��� ���� �г�

	public RouteInfoColumnNamePanel() {
		LayoutManager manager = new FlowLayout(FlowLayout.LEFT,0,0);
		
		setLayout(manager);
		
		//add(new RouteInfoColumnNameLabel("���Ź�ȣ"));
		add(new RouteInfoColumnNameLabel(" ��¥"));
		add(new RouteInfoColumnNameLabel(" �ð�"));
		add(new RouteInfoColumnNameLabel("�����"));
		add(new RouteInfoColumnNameLabel("������"));
		add(new RouteInfoColumnNameLabel("�¼���ȣ"));
		//remove(getComponentPopupMenu());
	}	
}
