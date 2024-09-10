package backgroundjobsystem.rest.dao;

import java.util.List;

import org.hibernate.Session;

import backgroundjobsystem.db.SessionManager;
import backgroundjobsystem.db.dao.AttemptsDao;
import backgroundjobsystem.db.dao.interfaces.IAttemptsDao;
import backgroundjobsystem.db.vo.AttemptsVo;

public class AttemptsRestDao {
	private IAttemptsDao attemptsDao = new AttemptsDao();

	public void addAttempt(AttemptsVo av) {
		Session session = SessionManager.getInstance().openSession();
		attemptsDao.insertAttempt(av, session);
		session.close();
	}

	public List<AttemptsVo> getAttemptsByJob(long jobId) {
		Session session = SessionManager.getInstance().openSession();
		List<AttemptsVo> lst = attemptsDao.getAttemptsByJob(jobId, session);
		session.close();

		return lst;
	}
	
	public List<AttemptsVo> getAttemptsByStatus(boolean successful) {
		Session session = SessionManager.getInstance().openSession();
		List<AttemptsVo> lst = attemptsDao.getAttemptsByStatus(successful, session);
		session.close();

		return lst;
	}
}
