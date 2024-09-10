package backgroundjobsystem.rest.service;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import backgroundjobsystem.db.vo.AttemptsVo;
import backgroundjobsystem.db.vo.JobsVo;
import backgroundjobsystem.db.vo.QueueVo;
import backgroundjobsystem.rest.dao.AttemptsRestDao;
import backgroundjobsystem.rest.dao.JobsRestDao;
import backgroundjobsystem.rest.dao.QueueRestDao;


@Path("/jobservice")
public class BackgroundJobSystemService {
	public static final String SUCCESS_MESSAGE = "</result>SUCCESS</result>";
	public static final String FAILURE_MESSAGE = "</result>FAILURE</result>";

	private JobsRestDao jobsRestDao = new JobsRestDao();
	private AttemptsRestDao attemptsRestDao = new AttemptsRestDao();
	private QueueRestDao queueRestDao = new QueueRestDao();

	@PostMapping(name="/addJob", produces="application/json", consumes="application/xml")
	public void addJob(@FormParam("name") String name, @FormParam("maxRetryAttempts") int maxRetryAttempts) {
		JobsVo jv = new JobsVo();
		jv.setName(name);
		jv.setMaxRetryAttempts(maxRetryAttempts);
		jobsRestDao.addJob(jv);
	}

	@GetMapping(name="/getAllJobs", produces="application/json", consumes="application/xml")
	public List<JobsVo> getAllJobs() {
		return jobsRestDao.getAllJobs();
	}

	@GetMapping(name="/getQueue", produces="application/json", consumes="application/xml")
	public QueueVo getQueue(@FormParam("jobId") long jobId, @FormParam("startTime") Timestamp startTime) {
		return queueRestDao.selectQueue(jobId, startTime);
	}

	@GetMapping(name="/getFullQueue", produces="application/json", consumes="application/xml")
	public List<QueueVo> getFullQueue() {
		return queueRestDao.selectFullQueue();
	}
	
	@PostMapping(name="/enqueue", produces="application/json", consumes="application/xml")
	public void enqueue(@FormParam("jobId") long jobId, @FormParam("startTime") Timestamp startTime, @FormParam("priority") byte priority) {
		QueueVo qvo = new QueueVo();
		qvo.setJob(jobId);
		qvo.setStartTime(startTime);
		qvo.setPriority(priority);

		queueRestDao.enqueue(qvo);
	}
	
	@DeleteMapping(name="/dequeue", produces="application/json", consumes="application/xml")
	public int dequeue(@FormParam("jobId") long jobId, @FormParam("startTime") Timestamp startTime, @FormParam("priority") byte priority) {
		QueueVo qvo = new QueueVo();
		qvo.setJob(jobId);
		qvo.setStartTime(startTime);
		qvo.setPriority(priority);

		return queueRestDao.deleteQueue(qvo);
	}

	@PostMapping(name="/addAttempt", produces="application/json", consumes="application/xml")
	public void addAttempt(@FormParam("jobId") long id, @FormParam("startTime") Timestamp startTime,
			@FormParam("endTime") Timestamp endTime, @FormParam("successful") boolean successful) {
		AttemptsVo av = new AttemptsVo();
		av.setJob(id);
		av.setStartTime(startTime);
		av.setEndTime(endTime);
		av.setSuccessful(successful);

		attemptsRestDao.addAttempt(av);
	}
}