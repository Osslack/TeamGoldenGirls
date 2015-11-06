/**
 * @author Jendrik
 */
package sample.input;

public class ControlSet {
	private int[][] m_actions;
	public ControlSet(){
		m_actions=new int[255][2];
		for(int i=0;i<255;i++){
			m_actions[i][0]=-1;
			m_actions[i][1]=-1;
		}
	}
	public void setControl(int keycode,int eventcodepressed, int eventcodereleased){
		m_actions[keycode][0] = eventcodepressed;
		m_actions[keycode][1] = eventcodereleased;
	}
	public int onKeyPressed(int keycode){
		return m_actions[keycode][0];
	}
	public int onKeyReleased(int keycode){
		return m_actions[keycode][1];
	}
}
