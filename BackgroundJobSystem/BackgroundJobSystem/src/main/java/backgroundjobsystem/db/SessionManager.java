package backgroundjobsystem.db;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import backgroundjobsystem.db.vo.AttemptsVo;
import backgroundjobsystem.db.vo.JobsVo;
import backgroundjobsystem.db.vo.QueueVo;

public class SessionManager {
	private static final int MAX_ALLOWED_SESSIONS = 5;
	private static final SessionManager instance = new SessionManager();
	private SessionFactory factory;
	private ArrayList<Session> sessionPool;

	public static SessionManager getInstance() {
		return instance;
	}

	//change later to use session pool
	public Session openSession() {
		if(sessionPool.size() < MAX_ALLOWED_SESSIONS) {
			Session s = factory.openSession();
			sessionPool.add(s);
			return s;
		}

		return null;
	}
	
	public void closeSession(Session session) {
		if ((session != null) && sessionPool.contains(session)) {
			session.close();
			sessionPool.remove(session);
		}
	}

	private SessionManager() {
		init();
	}

	//configuring in code so I don't have to mess with property files
	private void init() {
		sessionPool = new ArrayList<Session>();
		
		Configuration config = new Configuration();
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
		config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/DATABASE_NAME_HERE");
		config.setProperty("hibernate.connection.username", "pgroot");
		config.setProperty("hibernate.connection.password", "This_1_is_PGROOTs_Password.");
		config.setProperty("hibernate.connection.pool_size", String.valueOf(MAX_ALLOWED_SESSIONS));
		config.setProperty("hibernate.connection.autocommit", "false");
		config.setProperty("hibernate.show_sql", "true");
		
		config.addAnnotatedClass(JobsVo.class);
		config.addAnnotatedClass(QueueVo.class);
		config.addAnnotatedClass(AttemptsVo.class);
	}	
}