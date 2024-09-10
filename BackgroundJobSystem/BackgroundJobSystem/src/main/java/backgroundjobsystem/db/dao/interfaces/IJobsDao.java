package backgroundjobsystem.db.dao.interfaces;

import java.util.List;

import org.hibernate.Session;

import backgroundjobsystem.db.vo.JobsVo;

public interface IJobsDao {
	public long insertJob(JobsVo jobVo, Session session);
	public JobsVo selectJob(long id, Session session);
	public List<JobsVo> selectAllJobs(Session session);
	///public int deleteJob(JobsVo jobVo, Session session);
}
