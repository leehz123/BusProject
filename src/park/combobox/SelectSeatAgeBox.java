package park.combobox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import hong.SaveInfo;
import park.ReservationInfo;

public class SelectSeatAgeBox extends JComboBox<String>{ // ���ɺ� �¼������� �� �޺��ڽ�
	
	SaveInfo user;
	String[] ageGroup = {"����", "û�ҳ�", "�Ƶ�"};
	String age;
	int seatCnt;
	int seatID;
	double discountRate=0;
	String seatName;
	
	
	public SelectSeatAgeBox(SaveInfo user, int seatCnt) { // ���������� ���� �¼� �߰��ؾ��ؼ� Ŭ���� �״�� �̲������ � �¼��� ���� �޺��ڽ����� Ȯ���ؾ��ؼ� �迭 �ε����� �� seatnum �����´�
		this.user = user;
		this.seatCnt = seatCnt;
		seatName = user.getSeatNames().get(seatCnt);
		
		if(((Integer.parseInt(user.getSeatNames().get(seatCnt))) + 1)<10) {
			addItem(String.format("0%d�� �¼�", (Integer.parseInt(user.getSeatNames().get(seatCnt))) + 1)); // i��° Ű ���� �ش��ϴ� �¼� ��ȣ�� ���� ���� ǥ��
		}else {
			addItem(String.format("%d�� �¼�", (Integer.parseInt(user.getSeatNames().get(seatCnt))) + 1)); // i��° Ű ���� �ش��ϴ� �¼� ��ȣ�� ���� ���� ǥ��
		}
		addItem("------");
		this.seatID = user.getSeatNameBySeatId().get(seatName);
		for(int i=0;i<ageGroup.length;i++) { // ���� û�ҳ� ��� �߰�
			addItem(ageGroup[i]);
		}
		setName(seatName);
		setBackground(Color.white);
		setSize(10, 10);
		selectSeat();
	}

	void selectSeat() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				age = (String) getSelectedItem().toString(); // �޺��ڽ����� �� ���� string���� ��ȯ
				if(age.equals("------")||age.equals(String.format("0%d�� �¼�", (Integer.parseInt(user.getSeatNames().get(seatCnt))) + 1))
						||age.equals(String.format("%d�� �¼�", (Integer.parseInt(user.getSeatNames().get(seatCnt))) + 1))) {// Ű ���� ���ų� ---- ���� �ٽ� �����ϰ�
					//user.setTotalChargeCheck(false);
					//user.getDcBySeat().remove(seatID);
					user.getDcBySeat().put(seatID, null); // �ش� �¼��� ���η��� null�� �ٲ۴�
					new JOptionPane().showMessageDialog(null, "����, û�ҳ�, �Ƶ� �� �����ϼ���.","����",JOptionPane.ERROR_MESSAGE); // ���� ��� û�ҳ� ���� �����ϸ� ����â �߰�
	
				}else {
					discount(age);
					user.getdcBySeat().put(seatID, discountRate);  // �ش� �¼���ȣ �ڸ��� ������ �ִ´�
					System.out.println(user.getdcBySeat().get(user.getSeatNameBySeatId().get(seatName))+" ���� �Ϸ� " ); // �ؽ��ʿ� ����Ǵ��� Ȯ��

				}
			}
		});
	}
	
	void discount(String age) {
		if(age.equals("����")) {
			discountRate = 1;
			user.setAgeBySeat(user.getSeatNameBySeatId().get(seatName), age);
			//user.setTotalChargeCheck(true);
		}else if(age.equals("û�ҳ�")) {
			discountRate = 0.85;
			user.setAgeBySeat(user.getSeatNameBySeatId().get(seatName), age);
			//user.setTotalChargeCheck(true);
		}else if(age.equals("�Ƶ�")){
			discountRate = 0.7;
			user.setAgeBySeat(user.getSeatNameBySeatId().get(seatName), age);
			//user.setTotalChargeCheck(true);
		}
	}
}
