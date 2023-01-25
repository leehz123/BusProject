package hong.selectroute.event;

import java.awt.event.ItemEvent;

import hong.selectroute.SelectRouteMainFrame;

public class MonthComboBoxEvent extends RouteMainFrameItem {
	
	// ���� ������ �� �ִ� �޺��ڽ�
			
	public MonthComboBoxEvent(SelectRouteMainFrame main) {
		super(main);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// ���� �Ǹ� true
		int month;
		if(e.getStateChange() == 1) {
			month = (int)e.getItem();
			main.addDays(month);
			// main�� �ִ� addDays �޼��忡 ���õ� ���� int�� ����
		} else {
			// ������ ���� ��� �߰��ǹǷ� main�� �ִ� �޼���� ��� ���ŵ� ������Ѵ�.
			month = 0;
			main.resetDays();
		}
		main.setMonth(month);
		
	}
	
}
