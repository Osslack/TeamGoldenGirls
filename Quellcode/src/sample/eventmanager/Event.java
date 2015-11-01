package eventmanager;
import eventmanager.Eventdata;
public class Event {
	private Eventdata m_Data;
	private int m_ID;
	
	public Event(int id, Eventdata data){
		m_ID = id;
		if(data != null){
			data.m_ID = id;
		}
		m_Data = data;
	}
	
	public Eventdata getData(){
		return m_Data;
	}
	
	public int getID(){
		return m_ID;
	}
}
