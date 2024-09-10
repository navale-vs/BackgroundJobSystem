package backgroundjobsystem.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import backgroundjobsystem.db.dao.interfaces.IJobsDao;
import backgroundjobsystem.db.vo.JobsVo;

public class JobsDao implements IJobsDao {
	private static final String SELECT_HQL = "FROM JOBSVO WHERE id = :id";
	private static final String SELECT_ALL_HQL = "FROM JOBSVO";
//	private static final String SELECT_MAX_ID_HQL = "SELECT MAX(ID) FROM JOBSVO";

	@Override
	public long insertJob(JobsVo jobVo, Session session) {
		Transaction txn = session.getTransaction();
		session.persist("JobsVo", jobVo);
		txn.commit();

//		Query<Long> q = session.createQuery(SELECT_MAX_ID_HQL, Long.class);
//		List<Long> lst = q.list();
//		txn.commit();

//		return ((lst == null) || lst.isEmpty() ? null : lst.get(0));

		JobsVo jvAdded = (JobsVo) session.getIdentifier(jobVo);
		txn.commit();
		return ((jvAdded == null) ? null : jvAdded.getId());
	}

	@Override
	public JobsVo selectJob(long id, Session session) {
		Transaction txn = session.getTransaction();
//		Query<JobsVo> q = session.createQuery(SELECT_HQL, JobsVo.class);
//		q.setParameter("id", id);
//		List<JobsVo> lst = q.list();
		JobsVo jv = session.getReference(JobsVo.class, id);
		txn.commit();

		return jv;
//		return ((lst == null) || lst.isEmpty() ? null : lst.get(0));
	}

	@Override
	public List<JobsVo> selectAllJobs(Session session) {
		Transaction txn = session.getTransaction();
		Query<JobsVo> q = session.createQuery(SELECT_ALL_HQL, JobsVo.class);
		List<JobsVo> lst = q.list();
		txn.commit();
		
		return lst;
	}
}