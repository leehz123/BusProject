package hong.selectroute;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;

public class DayComboBox extends JComboBox<Integer> {
	
	final private static int FIRST_DAY = 1; // ��� ���� ù°���� 1��..
	private int lastDay;	// 1�Ϻ��� ������������ �޺��ڽ��� �߰��� ���̹Ƿ�
	private Dimension dimension = new Dimension(110,30);
	
	public DayComboBox() {
		setBackground(Color.WHITE);
		addItem(null);
		setPreferredSize(dimension);
	}
	
	// ������(��)���� �߰��ϴ� �޼��� �� �������� �Ű������� �Է¹޴´�.
	public void addItems(int month) {
		// �޿� ���� lastDay ���� �ʱ�ȭ
		switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				lastDay = 31;
				break;
			case 2:
				lastDay = 28;
				break;
			case 4: case 6: case 9: case 11:
				lastDay = 30;
				break;
			default:
				break;
		}
		
		// 1�Ϻ��� ������������ �޺��ڽ��� �߰�
		for(int i = FIRST_DAY; i <= lastDay; ++i) {
			addItem(i);
		}	
	}
	
	// �޺��ڽ��� ����ִ� �޼��� (������ ���� �ٲܶ����� ��� �߰�����..)
	public void reset() {
		removeAllItems();
	}

}
