package exavalu.com.entities;

import java.io.Serializable;

public class Practice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int practiceId;
	private String practiceName;
	public int getPracticeId() {
		return practiceId;
	}
	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}
	public String getPracticeName() {
		return practiceName;
	}
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	
	
}
