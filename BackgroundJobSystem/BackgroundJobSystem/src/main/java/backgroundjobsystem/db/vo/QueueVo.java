package backgroundjobsystem.db.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="queue", schema="public", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"job", "start_time"})})
public class QueueVo implements Serializable, Comparable<QueueVo> {
	@Id
	@Column(name="job")
	@XmlElement
	private long job;

	@Id
	@Column(name="start_time")
	@XmlElement
	private Timestamp startTime;

	@Column(name="priority")
	@XmlElement
	private byte priority;

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

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = (byte) Math.abs(priority);
	}

	@Override
	//startTime, then priority, then job 
	public int compareTo(QueueVo o) {
		if((o == null) || (o.getStartTime() == null)) {
			return 0;
		} else if (startTime == null) {
			return 1;
		}

		if(startTime.equals(o.startTime)) {
			if(priority == o.getPriority()) {
				return Long.valueOf(job).compareTo(o.getJob()); 
			} else {
				return Byte.valueOf(priority).compareTo(o.getPriority());
			}
		}

		return startTime.compareTo(o.startTime);
	}	
}