package backgroundjobsystem.rest.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import backgroundjobsystem.db.SessionManager;
import backgroundjobsystem.db.dao.QueueDao;
import backgroundjobsystem.db.dao.interfaces.IQueueDao;
import backgroundjobsystem.db.vo.QueueVo;

public class QueueRestDao {
	private IQueueDao queueDao = new QueueDao();

	public void enqueue(QueueVo queueVo) {
		Session session = SessionManager.getInstance().openSession();
		queueDao.insertQueue(queueVo, session);
		session.close();
	}

	public QueueVo selectQueue(long jobId, Timestamp startTime) {
		Session session = SessionManager.getInstance().openSession();
		QueueVo qvo = queueDao.selectQueue(jobId, startTime, session);
		session.close();
		
		return qvo;
	}

	public List<QueueVo> selectQueueByJob(long jobId) {
		Session session = SessionManager.getInstance().openSession();
		List<QueueVo> lst = queueDao.selectQueueByJob(jobId, session);
		session.close();
		
		return lst;
	}

	public List<QueueVo> selectQueueInDateRange(Timestamp startRange, Timestamp endRange) {
		Session session = SessionManager.getInstance().openSession();
		List<QueueVo> lst = queueDao.selectQueueInDateRange(startRange, endRange, session);
		session.close();
		
		return lst;
	}

	public List<QueueVo> selectFullQueue() {
		Session session = SessionManager.getInstance().openSession();
		List<QueueVo> lst = queueDao.selectFullQueue(session);
		session.close();
		
		return lst;
	}

	public int deleteQueue(QueueVo queueVo) {
		Session session = SessionManager.getInstance().openSession();
		int count = queueDao.deleteQueue(queueVo, session);
		session.close();
		
		return count;
	}
}