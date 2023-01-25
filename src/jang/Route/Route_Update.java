package jang.Route;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jang.Route_DB;

public class Route_Update extends JFrame {

	JPanel panel = new JPanel();

	JButton btn1 = new JButton("����");
	JButton btn4 = new JButton("Ȯ��");

	JLabel bus = new JLabel("���� ����");

	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();

	JLabel l1 = new JLabel("��¥ : ");
	JLabel l2 = new JLabel("�ð� : ");
	JLabel l3 = new JLabel("�뼱 ID : ");
	JLabel l4 = new JLabel("���� ID : ");

	JLabel pName = new JLabel("�뼱 �߰�");

	Route_DB db = new Route_DB();

	public Route_Update() {
		Route_Update();
	}

	public void Route_Update() {
		setTitle("����");
		add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		setResizable(false);
		setBounds(10, 20, 370, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

		pName.setBounds(100, 30, 200, 50);
		pName.setFont(new Font("�޸ո���ü", Font.BOLD, 40));
		panel.add(pName);

		btn1.setBounds(90, 220, 70, 30);
		btn1.setBackground(new Color(0XE7E6E6));
		btn4.setBounds(180, 220, 70, 30);
		btn4.setBackground(new Color(0XE7E6E6));

		panel.add(btn1);
		panel.add(btn4);

		bus.setBounds(20, 100, 80, 30);
		bus.setFont(new Font("�޸ո���ü", Font.BOLD, 20));
		panel.add(bus);

		// �Է� ���� ��
		tf1.setBounds(80, 170, 80, 25);
		tf1.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf1); // ��¥
		tf2.setBounds(240, 170, 80, 25);
		tf2.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf2); // �ð�
		tf3.setBounds(240, 140, 80, 25);
		tf3.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf3); // �뼱 ID
		tf4.setBounds(80, 140, 80, 25);
		tf4.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(tf4); // ���� ID

		// �Է� ���� �� �̸�
		l1.setBounds(20, 170, 80, 30);
		l1.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l1); // ��¥
		l2.setBounds(180, 170, 80, 30);
		l2.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l2); // �ð�
		l3.setBounds(180, 140, 80, 30);
		l3.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l3); // �뼱 ID
		l4.setBounds(20, 140, 80, 30);
		l4.setFont(new Font("�޸ո���ü", Font.PLAIN, 15));
		panel.add(l4); // ���� ID

		// ���� ��ư �̺�Ʈ
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int bi_id = Integer.parseInt(tf4.getText());
				String bi_day = tf1.getText();
				String bi_time = tf2.getText();
				int rt_id = Integer.parseInt(tf3.getText());

//				db.updateData(new Businfo_Data(bi_id, bi_day, bi_time, rt_id));

				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");

			}
		});

		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}

}
