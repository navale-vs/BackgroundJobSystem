package backgroundjobsystem.db.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="jobs", schema="public", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
@XmlRootElement(name="jobs")
public class JobsVo implements Serializable, Comparable<JobsVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2679489408714952005L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	private long id;

	@Column(name="name")
	@XmlElement
	private String name;

	@XmlElement
	private int maxRetryAttempts;

	@Column(name="last_run_time")
	@XmlElement
	private Timestamp lastRunTime;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getLastRunTime() {
		return lastRunTime;
	}
	public void setLastRunTime(Timestamp lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

	public int getMaxRetryAttempts() {
		return maxRetryAttempts;
	}
	public void setMaxRetryAttempts(int maxRetryAttempts) {
		this.maxRetryAttempts = maxRetryAttempts;
	}

	@Override
	public int compareTo(JobsVo jvOther) {
		if (jvOther == null) {
			return 0;
		}

		return name.compareTo(jvOther.getName());
	}
}
