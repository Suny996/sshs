import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kettleTaskJob {
     private static Logger LOGGER = LogManager.getLogger(kettleTaskJob.class.getName());

      public void run() throws Exception {
          LOGGER.info("*****kettle定时任务运行开始******");
          String transFileName = "D:/test.ktr";
          KettleUtil.callNativeTrans(transFileName);
          LOGGER.info("*****kettle定时任务运行结束******");
      }
     
      public static void main(String[] args) throws Exception {
            kettleTaskJob job = new kettleTaskJob();
            job.run();
     }
}