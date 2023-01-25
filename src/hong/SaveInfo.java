package hong;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class SaveInfo {
	
	private String userId;
	private Integer busId;
	private ArrayList<Integer> reservationId = new ArrayList<>(); // �¼��� ���Ź�ȣ 1���� �����¼��� �� �����Ƿ� �迭�� ����
	private String depart;
	private String arrive;
	private String price;
	private String date;
	private String time;
	private int rt_id;
	private LinkedHashMap<Integer, Double> dcBySeat = new LinkedHashMap<>();// �¼��� ���η�
	private LinkedHashMap<Integer, String> ageBySeat = new LinkedHashMap<>(); // �¼��� ����
	private LinkedHashMap<String, Integer> seatNameBySeatId = new LinkedHashMap<>();// �¼��̸��� �ش��ϴ� �¼� id
	private ArrayList<Integer> seatId = new ArrayList<>(); // �¼� id��
	private ArrayList<String> seatNames = new ArrayList<>(); // �¼��̸� ����Ʈ // ����� �Ѱ��ֽô°�
	private String payWay; // �̰� �ѹ� �����Ҷ� ���� �������� �ϴϱ� �迭 x
	private double totalCharge=0; // ������ ���� �� �Ҽ��� ���ϱ� ������ double��
	private boolean totalChargeCheck = true;
	
	
//	public void set_seat_name(ArrayList<String> seat_name) {
//		this.seat_name = seat_name;
//	}
	
	public void setTotalChargeCheck(boolean totalChargeCheck) {
		this.totalChargeCheck = totalChargeCheck;
	}
	public boolean isTotalChargeCheck() {
		return totalChargeCheck;
	}
	public void set_price(Integer price) {
		this.price = price.toString();
	}
	
	public void set_bi_id(int bi_id) {
		this.busId = bi_id;
	}
	
	public void set_rt_id(int rt_id) {
		this.rt_id = rt_id;
	}
	
	public void set_depart_from(String depart_from) {
		this.depart = depart_from;
	}
	
	public void set_arrive_at(String arrive_at) {
		this.arrive = arrive_at;
	}
	
	public void set_user_id(String user_id) {
		this.userId = user_id;
	}
	
	public void set_date(int year, int month, int day) {
		this.date = String.format("%d/%02d/%02d", year-2000, month, day);
	}
	
	public void set_time(String time) {
		this.time = time;
	}
	
	public int get_bi_id() {
		return busId;
	}
	
	public String get_user_id() {
		return userId;
	}
	
	public void put_bs_id(int bs_id) { 
		this.dcBySeat.put(bs_id, null);
	}
	
	public void remove_bs_map(ArrayList<Integer> seatId, ArrayList<String> seatName) {
		int size = dcBySeat.size();
		System.out.println(seatId.size());
		System.out.println(dcBySeat.size());
		for(int i= size-1; i >= 0; --i) {
			dcBySeat.remove(seatId.get(i));
			seatNameBySeatId.remove(seatName.get(i));
			this.seatId.remove(i);
			this.seatNames.remove(i);
		}
	}
	
	public void removeIdMap(int bs_id) {
		this.dcBySeat.remove(bs_id);
		
	}
	
	public void removeNameMap(String bs_name) {
		this.seatNameBySeatId.remove(bs_name);
	}
	
	public void put_bs_name(String bs_name, int bs_id) {
		this.seatNameBySeatId.put(bs_name, bs_id);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setSeatNames() { 		// 
		Set<String> keys = seatNameBySeatId.keySet();
		for(String key : keys) {
			seatNames.add(key);
		}
		for(int i=0;i<seatNames.size();i++)
			System.out.println(i+"��° �¼�ID "+seatNames.get(i)+"����");

	}
	public LinkedHashMap<String, Integer> getSeatNameBySeatId() {
		return seatNameBySeatId;
	}
	
	public ArrayList<String> getSeatNames(){
		return seatNames;
	}
	public void setSeatId() { 
		Set<Integer> keys = dcBySeat.keySet();
		for(Integer key : keys) {
			seatId.add(key);
		}
		for(int i=0;i<seatId.size();i++)
			System.out.println("�¼�Id "+seatId.get(i)+"�߰�");
	}

	public ArrayList<Integer> getSeatId() {
		return seatId;
	}
	public int getSeatSize() {
		return dcBySeat.size();
	}
	
	
	public void setAgeBySeat(Integer seat, String age) {
		ageBySeat.put(seat, age);
	}
	public LinkedHashMap<Integer, String> getAgeBySeat() {
		return ageBySeat;
	}
	
	public LinkedHashMap<Integer, Double> getDcBySeat() {
		return dcBySeat;
	}
	
	public ArrayList<Integer> getReservationId() {
		return reservationId;
	}
	
	//---------------------------------------------
	public double getTotalCharge() { // price * dcBySeat.get(key) + price * dcBySeat.get(key)
		Set<Integer> keys = dcBySeat.keySet();
		for(Integer key : keys) { // Ű �ƴϰ� ��� �����ߵ�
			//totalCharge += Integer.parseInt(price)*(Double.parseDouble(dcBySeat.get(key))); // ����(����ȭ) * Ű �� �Ѱ��� �̾Ƽ� �� Ű�� ���(�Ǽ�ȭ)
			totalCharge += Integer.parseInt(price)*dcBySeat.get(key);
		}
		return totalCharge;
	}
	
	public void setTotalCharge() {
		totalCharge = 0;
	}
	public void setTotalCharge2() {
		totalCharge = 1;
	}
	
	public double getTotalCharge2() {
		return totalCharge;
	}
	
	
	public void setReservationId(int rvid) {
		reservationId.add(rvid);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getArrive() {
		return arrive;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public LinkedHashMap<Integer, Double> getdcBySeat() {
		return dcBySeat;
	}

	public void setdcBySeat(LinkedHashMap<Integer, Double> dcBySeat) {
		this.dcBySeat = dcBySeat;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public void setTotalCharge(double totalCharge) {
		this.totalCharge = totalCharge;
	}

	
	
	
}
