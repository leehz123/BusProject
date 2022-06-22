package park.button;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import park.ReservationInfo;
import park.SaveDB;
import park.frame.PayInfo;

public class PayButton extends JButton{

	CardLayout cardLayoutManager;

	
	public PayButton(CardLayout cl) {
		cardLayoutManager = cl;
	}
	
	public PayButton(ReservationInfo user) {
		super("���� �ϱ�");
		setBounds(190, 500, 100, 50);
		
		addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
	
				String str =String.format("���� �ݾ� : %d\n���� ���� : %s\n���� �Ͻðڽ��ϱ�?", (int)(user.getTotalCharge()), user.getPayWay());
				int ok =JOptionPane.showConfirmDialog(null, str, "���� Ȯ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(ok==JOptionPane.OK_OPTION) { // ok�� ������
					new SaveDB(user); // ��� �����Ѵ�
					new PayInfo(user); // ���� ��ư ������ ���� ȭ������ �Ѿ��
					
				}
				else { // ok �̿��� ���� ������
					user.setTotalCharge(); // ���� �˾����� ����� ���̹Ƿ� �ѿ���� �ʱ�ȭ ���ش�
					
				}
				
			}
		});
	}
}