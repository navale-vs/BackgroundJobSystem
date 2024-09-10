package backgroundjobsystem.db.vo;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="attempts", schema="public")
public class AttemptsVo implements Serializable, Comparable<AttemptsVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6732590100952237362L;

	@Id
	@Column(name="job")
	@XmlElement
	private long job;

	@Id
	@Column(name="start_time")
	@XmlElement
	private Timestamp startTime;

	@Column(name="end_time")
	@XmlElement
	private Timestamp endTime;

	@Column(name="successful")
	@XmlElement
	private boolean successful;

	
	public long getJob() {
		return job;
	}

	public void setJob(long job) {
		this.job = job;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	@Override
	//startTime, then job
	public int compareTo(AttemptsVo o) {
		if((o == null) || (o.getStartTime() == null)) {
			return 0;
		} else if (startTime == null) {
			return 1;
		}

		if(startTime.equals(o.startTime)) {
			return Long.valueOf(job).compareTo(o.getJob()); 
		}

		return startTime.compareTo(o.startTime);
	}
}