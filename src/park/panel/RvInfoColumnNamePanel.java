package park.panel;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import park.label.RvInfoColumnNameLabel;

public class RvInfoColumnNamePanel extends JPanel{
	public RvInfoColumnNamePanel() {
	LayoutManager manager = new FlowLayout(FlowLayout.LEFT,0,0);
		
		setLayout(manager);
		
		add(new RvInfoColumnNameLabel("���Ź�ȣ"));
		add(new RvInfoColumnNameLabel(" ��¥"));
		add(new RvInfoColumnNameLabel(" �ð�"));
		add(new RvInfoColumnNameLabel("�����"));
		add(new RvInfoColumnNameLabel("������"));
		add(new RvInfoColumnNameLabel("�¼���ȣ"));
	}

}
