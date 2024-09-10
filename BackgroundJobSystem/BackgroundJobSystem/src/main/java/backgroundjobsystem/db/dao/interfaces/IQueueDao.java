package backgroundjobsystem.db.dao.interfaces;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import backgroundjobsystem.db.vo.QueueVo;

public interface IQueueDao {
	public void insertQueue(QueueVo queueVo, Session session);
	public QueueVo selectQueue(long jobId, Timestamp startTime, Session session);
	public List<QueueVo> selectQueueByJob(long jobId, Session session);
	public List<QueueVo> selectQueueInDateRange(Timestamp startRange, Timestamp endRange, Session session);
	public List<QueueVo> selectFullQueue(Session session);
	public int deleteQueue(QueueVo queueVo, Session session);
}