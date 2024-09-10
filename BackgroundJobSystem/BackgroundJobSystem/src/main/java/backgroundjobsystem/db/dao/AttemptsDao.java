package backgroundjobsystem.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import backgroundjobsystem.db.dao.interfaces.IAttemptsDao;
import backgroundjobsystem.db.vo.AttemptsVo;

public class AttemptsDao implements IAttemptsDao {
	private static final String SELECT_BY_JOB_ID_HQL = "FROM ATTEMPTSVO WHERE job = :jobId";
	private static final String SELECT_BY_STATUS_HQL = "FROM ATTEMPTSVO WHERE successful = :successful";

	@Override
	public int insertAttempt(AttemptsVo attemptVo, Session session) {
		Transaction txn = session.getTransaction();
		session.persist(attemptVo);
		txn.commit();

		return 1;
	}

	@Override
	public List<AttemptsVo> getAttemptsByJob(long jobId, Session session) {
		Transaction txn = session.getTransaction();
		Query<AttemptsVo> q = session.createQuery(SELECT_BY_JOB_ID_HQL, AttemptsVo.class);
		q.setParameter("job", jobId);
		List<AttemptsVo> lst = q.list();
		txn.commit();
		
		return lst;
	}

	@Override
	public List<AttemptsVo> getAttemptsByStatus(boolean successful, Session session) {
		Transaction txn = session.getTransaction();
		Query<AttemptsVo> q = session.createQuery(SELECT_BY_STATUS_HQL, AttemptsVo.class);
		q.setParameter("successful", successful);
		List<AttemptsVo> lst = q.list();
		txn.commit();
		
		return lst;
	}

	@Override
	public int deleteAttempt(AttemptsVo attempt, Session session) {
		session.remove(attempt);
		return 1;
	}

}
