package com.cn.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
/**
 * An ApplicationStartingEvent is sent at the start of a run but before any processing, except for the registration of listeners and initializers.
 * An ApplicationEnvironmentPreparedEvent is sent when the Environment to be used in the context is known but before the context is created.
 * An ApplicationPreparedEvent is sent just before the refresh is started but after bean definitions have been loaded.
 * An ApplicationStartedEvent is sent after the context has been refreshed but before any application and command-line runners have been called.
 * An ApplicationReadyEvent is sent after any application and command-line runners have been called. It indicates that the application is ready to service requests.
 * An ApplicationFailedEvent is sent if there is an exception on startup.
 * @author GJB
 *
 */
public class MyListener implements ApplicationListener<ApplicationStartingEvent>{
	
	public void onApplicationEvent(ApplicationStartingEvent arg0) {
		 System.out.println(getClass().getSimpleName());
	}
	
}
