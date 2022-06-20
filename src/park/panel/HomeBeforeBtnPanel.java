package park.panel;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

import park.button.BeforeButton;
import park.button.HomeButton;

public class HomeBeforeBtnPanel extends JPanel{ // 홈버튼, 이전버튼 일렬로 나타낼 패널

	HomeButton homeBtn = new HomeButton();
	BeforeButton befBtn = new BeforeButton();
	
	public HomeBeforeBtnPanel() {
		LayoutManager manager = new FlowLayout(FlowLayout.LEFT); // 판넬의 레이아웃을 왼쪽부터 일렬로 컴포넌트를 출력하는 형태로 설정
		setLayout(manager);
		add(homeBtn);
		add(befBtn);
			
	
		setBounds(10, 10, 500, 60);
	}
}
