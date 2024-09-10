package backgroundjobsystem.rest.dao;

import java.util.List;

import org.hibernate.Session;

import backgroundjobsystem.db.SessionManager;
import backgroundjobsystem.db.dao.JobsDao;
import backgroundjobsystem.db.dao.interfaces.IJobsDao;
import backgroundjobsystem.db.vo.JobsVo;

public class JobsRestDao {
	private IJobsDao jobsDao = new JobsDao();

	public long addJob(JobsVo jobVo) {
		Session session = SessionManager.getInstance().openSession();
		long id = jobsDao.insertJob(jobVo, session);
		session.close();
		
		return id;
	}
	
	public JobsVo getJob(long id) {
		Session session = SessionManager.getInstance().openSession();
		JobsVo jv = jobsDao.selectJob(id, session);
		session.close();
		
		return jv;
	}

	public List<JobsVo> getAllJobs() {
		Session session = SessionManager.getInstance().openSession();
		List<JobsVo> lst = jobsDao.selectAllJobs(session);
		session.close();
		
		return lst;
	}
}