package hong.selectroute.event;

import java.awt.event.ItemEvent;

import hong.selectroute.SelectRouteMainFrame;

public class DayComboBoxEvent extends RouteMainFrameItem {
	
	// ��¥�� ������ �� �ִ� �޺��ڽ�
	
	public DayComboBoxEvent(SelectRouteMainFrame main) {
		super(main);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		int day;
		
		// �޺��ڽ��� ���õǸ� true
		if(e.getStateChange() == 1) {
			day = (int)e.getItem();
		} else {
			day = 0;
		}
		
		main.setDay(day);
	}
}
