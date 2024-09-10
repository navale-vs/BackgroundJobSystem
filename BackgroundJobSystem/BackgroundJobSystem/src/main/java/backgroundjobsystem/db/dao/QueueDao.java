package backgroundjobsystem.db.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import backgroundjobsystem.db.dao.interfaces.IQueueDao;
import backgroundjobsystem.db.vo.QueueVo;

public class QueueDao implements IQueueDao {
	private static final String SELECT_HQL = "FROM QUEUEVO WHERE job = :jobId AND start_time = :startTime";
	private static final String SELECT_BY_JOB_ID_HQL = "FROM QUEUEVO WHERE job = :jobId AND start_time = :startTime";
	private static final String SELECT_BY_IN_DATE_RANGE = "FROM QUEUEVO WHERE start_time BETWEEN :startRange AND :endRange";
	private static final String SELECT_ALL_HQL = "FROM QUEUEVO"; //implement paging?
	private static final String DELETE_HQL = "DELETE FROM QUEUEVO WHERE job = :jobId AND start_time = :startTime AND priority = :priority";

	@Override
	public void insertQueue(QueueVo queueVo, Session session) {
		Transaction txn = session.beginTransaction();
		session.persist("QueueVo", queueVo);
		txn.commit();
	}

	@Override
	public QueueVo selectQueue(long jobId, Timestamp startTime, Session session) {
		Transaction txn = session.beginTransaction();
		Query<QueueVo> q = session.createQuery(SELECT_HQL, QueueVo.class);
		q.setParameter("jobId", jobId);
		q.setParameter("startTime", startTime);
		List<QueueVo> lst = q.list();
		txn.commit();

		return ((lst == null) || lst.isEmpty() ? null : lst.get(0));
	}

	@Override
	public List<QueueVo> selectFullQueue(Session session) {
		Transaction txn = session.beginTransaction();
		Query<QueueVo> q = session.createQuery(SELECT_ALL_HQL, QueueVo.class);
		List<QueueVo> lst = q.list();
		txn.commit();
		
		return lst;
	}

	@Override
	public int deleteQueue(QueueVo queueVo, Session session) {
		Transaction txn = session.beginTransaction();
		Query<QueueVo> q = session.createQuery(DELETE_HQL, QueueVo.class);
		q.setParameter("jobId", queueVo.getJob());
		q.setParameter("startTime", queueVo.getStartTime());
		q.setParameter("priority", queueVo.getPriority());
		int status = q.executeUpdate();
		txn.commit();
		
		return status;
	}

	@Override
	public List<QueueVo> selectQueueByJob(long jobId, Session session) {
		Transaction txn = session.beginTransaction();
		Query<QueueVo> q = session.createQuery(SELECT_HQL, QueueVo.class);
		q.setParameter("jobId", jobId);
		List<QueueVo> lst = q.list();
		txn.commit();

		return lst;
	}

	@Override
	public List<QueueVo> selectQueueInDateRange(Timestamp startRange, Timestamp endRange, Session session) {
		Transaction txn = session.beginTransaction();
		Query<QueueVo> q = session.createQuery(SELECT_BY_IN_DATE_RANGE, QueueVo.class);
		q.setParameter("startRange", startRange);
		q.setParameter("endRange", endRange);
		List<QueueVo> lst = q.list();
		txn.commit();

		return lst;
	}
}