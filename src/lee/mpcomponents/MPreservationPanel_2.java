package lee.mpcomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import an.OjdbcConnection;
import hong.SaveInfo;
import lee.mpevents.MPcheckboxIL;
import lee.mpmodel.MPreservationlistModel;

//���ų��� ���� �� �г�
//��񿡼� ���ų��� ������ �� ���� ��¥ ������ ���ų����� �����;� ��...
public class MPreservationPanel_2 extends JPanel {
	 
	SaveInfo saveInfo;
	String user_id;// �ϴ� �ӽ÷�(�ٸ� �����ӿ��� ���޹��� ����) 
	
	
	JButton MPreservationcancleBtn = new JButton("�������");	
	BorderLayout MPborderlayout = new BorderLayout();
	
	//Ƽ�� Ʋ�� �Ǵ� �г� (�ȿ� ��, �n�� ��) 
	JPanel MPreservation_3 = new JPanel();
	
	JCheckBox[] cbArr;
	
	public MPreservationPanel_2(SaveInfo saveInfo) {  

		this.saveInfo = saveInfo;
		this.user_id = saveInfo.get_user_id();
		
		JScrollPane MPscroll = new JScrollPane(MPreservation_3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		BoxLayout MPboxlayout = new BoxLayout(MPreservation_3, BoxLayout.Y_AXIS);		
		
		setLayout(MPborderlayout);
		MPreservation_3.setLayout(MPboxlayout);
	
		//���� Ƽ�� ���� ���� ��������Ʈ�г� �߰�
		ArrayList<MPreservationlistPanel> MPreservationlistArrList = new ArrayList<>();
		
		int ticketNum = 0;
		ArrayList<MPreservationlistModel> sqlResults = null;
		try (Connection conn = OjdbcConnection.getConnection();){	
			//�̳����� �˻������ ����� ����           
			sqlResults = MPreservationlistModel.get(conn, user_id);
			ticketNum = sqlResults.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//üũ�ڽ� ���� �迭
		cbArr = new JCheckBox[ticketNum]; //Ƽ�ϼ���ŭ üũ�ڽ� ������ �Ŵϱ� 
		
		for(int i = 0; i < ticketNum; ++i) {  			
			
			int br_id = sqlResults.get(i).getBr_id();
			
			//���� ����Ʈ �г��� ý�� ����
			cbArr[i] = new JCheckBox(Integer.toString(br_id));//üũ�ڽ��� br_id�� �ؽ�Ʈ�� ���� ������ ũ�⸦ 18, 18�� �����ؼ� �� ���̰� ��
			cbArr[i].setBounds(400, 77, 18, 18);
			cbArr[i].addItemListener(new MPcheckboxIL()); //MPcheckboxIL �� �����۸����� ��� ���� Ŭ����. (�n�ڿ��� �����۸����� �� �� ����) 
			
			//���� ����Ʈ �г��� �� ����
			MPreservationlistArrList.add(new MPreservationlistPanel(br_id, sqlResults.get(i).toString()));
			MPreservationlistArrList.get(i).add(cbArr[i]);//üũ�ڽ��� ���⼭ �޾���
			
			MPreservation_3.add(MPreservationlistArrList.get(i)); //�ϼ��� ���Ÿ���Ʈ �г�(��+�n�� ��� ����) �� MPreservation_3(��ũ�� ���� JPanel) �� ���̱� 
			MPreservation_3.setPreferredSize(new Dimension(500, 180*ticketNum));//����Ʈ�г��� ���̿� Ƽ�ϼ� ������ߵʡ�
			//��ũ�� �۵��Ϸ��� setPreferredSize�� ������ ��������� �ߴ���
		}
		
		
		MPscroll.getVerticalScrollBar().setUnitIncrement(6);// ��ũ�� �ӵ� ����

		MPreservationcancleBtn.setBorderPainted(false);
		add(MPreservationcancleBtn, "South");
		add(MPscroll, "Center");
		
	}
	
}