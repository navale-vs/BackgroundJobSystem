package backgroundjobsystem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backgroundjobsystem.db.SessionManager;
import backgroundjobsystem.db.dao.AttemptsDao;
import backgroundjobsystem.db.dao.JobsDao;
import backgroundjobsystem.db.vo.AttemptsVo;
import backgroundjobsystem.db.vo.JobsVo;
import backgroundjobsystem.db.vo.QueueVo;
import backgroundjobsystem.rest.service.BackgroundJobSystemService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/jobs")
public class BackgroundJobSystemController {
	final protected BackgroundJobSystemService bjss = new BackgroundJobSystemService();
	final protected List<JobsVo> lstJobs = new ArrayList<JobsVo>();

	public BackgroundJobSystemController() {
		
	}

	@GetMapping(path="/getqueue")
	public void getQueue(Model model, @ModelAttribute("jobId") long jobId, @ModelAttribute("startTime") Timestamp startTime, HttpServletResponse response) {
		QueueVo qvo = bjss.getQueue(jobId, startTime);
		model.addAttribute(qvo);
	}

	@GetMapping(path="/fullqueue")
	public void getFullQueue(Model model, HttpServletResponse response) {
		model.addAttribute("fullQueue", bjss.getFullQueue());
	}
	
	@GetMapping(path="/alljobs")
	public void getAllJobs(Model model, HttpServletResponse response) {
		model.addAttribute("allJobs", bjss.getAllJobs());
	}

	@PostMapping(path = "/addjob")
	public Callable<String> addJob(Model model, @ModelAttribute("name") String name, @ModelAttribute("maxRetryAttempts") int maxRetryAttempts) {
		JobsVo jv = new JobsVo();
		jv.setName(name);
		jv.setMaxRetryAttempts(maxRetryAttempts);

		JobsDao jd = new JobsDao();
		long jobId = jd.insertJob(jv, SessionManager.getInstance().openSession());
		jv.setId(jobId);

		return new Callable<String>() {
			public String call() throws Exception {
				return "alljobs";
			}
		};
	}
	
	@PostMapping(path="/addrequest")
	public Callable<String> addRequest(Model model, @ModelAttribute("jobId") long jobId, @ModelAttribute("startTime") Timestamp startTime) {
		AttemptsVo avo = new AttemptsVo();
		avo.setJob(jobId);
		avo.setStartTime(startTime);
		
		AttemptsDao ad = new AttemptsDao();
		ad.insertAttempt(avo, SessionManager.getInstance().openSession());
		return new Callable<String>() {
			public String call() throws Exception {
				return "fullqueue";
			}
		};
	}

	@DeleteMapping(path="/deleterequest")
	public int deleteRequest(Model model, @ModelAttribute("jobId") long jobId, @ModelAttribute("startTime") Timestamp startTime) {
		return 0;
	}
}
