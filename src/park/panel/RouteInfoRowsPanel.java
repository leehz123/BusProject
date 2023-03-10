package park.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import hong.SaveInfo;
import park.label.RouteInfoRowsLabel;

public class RouteInfoRowsPanel extends JPanel{ // 행선지 정보 행들 넣는 패널 

	public RouteInfoRowsPanel(SaveInfo user) {
		LayoutManager manager = new FlowLayout(FlowLayout.LEFT);
		setPreferredSize(new Dimension(450, 30));
		setLayout(manager);
		for(int i=0;i<user.getSeatSize();i++) {
			add(new RouteInfoRowsLabel(user.getDate()));
			add(new RouteInfoRowsLabel(user.getTime()));
			add(new RouteInfoRowsLabel(user.getDepart()));
			add(new RouteInfoRowsLabel(user.getArrive()));
			add(new RouteInfoRowsLabel(String.format("%s",(Integer.parseInt(user.getSeatNames().get(i))+1 )))); // 좌석이름
		
		}
		
		//setBounds(10, 100, 450, 30);
		
	}
}
