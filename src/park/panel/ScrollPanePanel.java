package park.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import hong.SaveInfo;
import park.label.RouteInfoRowsLabel;

public class ScrollPanePanel extends JPanel {
	public ScrollPanePanel(SaveInfo user) {
		LayoutManager manager = new FlowLayout(FlowLayout.LEFT);
		setLayout(manager);
		//setPreferredSize(new Dimension(450, 100));
		for(int i=0;i<user.getSeatSize();i++) {
			add(new RouteInfoRowsLabel(user.getDate()));
			add(new RouteInfoRowsLabel(user.getTime()));
			add(new RouteInfoRowsLabel(user.getDepart()));
			add(new RouteInfoRowsLabel(user.getArrive()));
			add(new RouteInfoRowsLabel(String.format("%s",(Integer.parseInt(user.getSeatNames().get(i))+1 )))); // �¼��̸�
		
		}
		
		//getViewport().setBackground(Color.black);
		setBackground(Color.white);
	}

}
