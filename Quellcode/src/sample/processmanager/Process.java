/*unused*/

package sample.processmanager;

class Process {
	private       int          m_ID;
	private final Processowner m_Owner;
	private final long         m_Tickdelay;
	private       long         m_Waited;

	public Process(long tickrate, Processowner owner){
		m_Tickdelay = tickrate;
		m_Owner = owner;
	}

	public void Run(long elapsed){
		m_Waited += elapsed;
		while(m_Waited >= m_Tickdelay){
			m_Waited = 0;
			m_Owner.ProcessCallback(elapsed);
		}
	}

	public int getID(){
		return m_ID;
	}

	public void setID(int id){
		m_ID = id;
	}
}
