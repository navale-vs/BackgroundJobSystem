package backgroundjobsystem.db.dao.interfaces;

import java.util.List;

import org.hibernate.Session;

import backgroundjobsystem.db.vo.AttemptsVo;

public interface IAttemptsDao {
	public int insertAttempt(AttemptsVo attemptVo, Session session);
	public List<AttemptsVo> getAttemptsByJob(long jobId, Session session);
	public List<AttemptsVo> getAttemptsByStatus(boolean successful, Session session);
	public int deleteAttempt(AttemptsVo attemptVo, Session session);
}
