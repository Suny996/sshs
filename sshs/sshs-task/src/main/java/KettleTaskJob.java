import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Suny
 *
 */
public class KettleTaskJob {
	private static Logger LOGGER = LogManager.getLogger(KettleTaskJob.class);

	public void run() throws Exception {
		LOGGER.info("*****kettle定时任务运行开始******");
		String transFileName = "D:/test.ktr";
		KettleUtil.callNativeTrans(transFileName);
		LOGGER.info("*****kettle定时任务运行结束******");
	}

	public static void main(String[] args) throws Exception {
		KettleTaskJob job = new KettleTaskJob();
		job.run();
	}
}