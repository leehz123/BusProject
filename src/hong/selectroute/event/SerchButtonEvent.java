package hong.selectroute.event;

import java.awt.event.ActionEvent;

import hong.selectroute.SelectRouteMainFrame;

public class SerchButtonEvent extends RouteMainFrameAction {
	
	// ���, ��¥�� �´� ������ ��ȸ�� �� �ִ� ��ư �̺�Ʈ
	
	public SerchButtonEvent(SelectRouteMainFrame main) {
		super(main);
	}
	
	@Override
	// �����, ������, ��¥�� �Է��ؾ� �Ѵ� ���ϸ� ���޼��� �ߵ���.
	public void actionPerformed(ActionEvent e) {
		if(main.checkRoute() && main.checkDate()) {
			
			if(main.checkMonth()) {
				main.getRouteID();
				main.showBus();
				main.getprice();			
				
			}
		}
	}

}
