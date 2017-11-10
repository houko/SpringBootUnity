package info.xiaomo.scheduled.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author : xiaomo
 */
@Component
@Slf4j
public class ScheduledTasks {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    // 定义某个定时任务
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.debug("每5秒钟执行一次，注意观察日志时间");
    }


    /**
     * 字段	 	允许值	 	允许的特殊字符
     * 秒	 	0-59	 	, - * /
     * 分	 	0-59	 	, - * /
     * 小时	 	0-23	 	, - * /
     * 日期	 	1-31	 	, - *   / L W C
     * 月份	 	1-12 或者 JAN-DEC	 	, - * /
     * 星期	 	1-7 或者 SUN-SAT	 	, - *   / L C #
     * 年（可选）	 	留空, 1970-2099	 	, - * /
     * <p>
     * “*”字符被用来指定所有的值。如：”*“在分钟的字段域里表示“每分钟”。
     * “-”字符被用来指定一个范围。如：“10-12”在小时域意味着“10点、11点、12点”。
     * “,”字符被用来指定另外的值。如：“MON,WED,FRI”在星期域里表示”星期一、星期三、星期五”.
     * “?”字符只在日期域和星期域中使用。它被用来指定“非明确的值”。当你需要通过在这两个域中的一个来指定一些东西的时候，它是有用的。看下面的例子你就会明白。
     * “L”字符指定在月或者星期中的某天（最后一天）。即“Last ”的缩写。但是在星期和月中“Ｌ”表示不同的意思，如：在月子段中“L”指月份的最后一天-1月31日，2月28日，如果在星期字段中则简单的表示为“7”或者“SAT”。如果在星期字段中在某个value值得后面，则表示“某月的最后一个星期value”,如“6L”表示某月的最后一个星期五。
     * “W”字符只能用在月份字段中，该字段指定了离指定日期最近的那个星期日。
     * “#”字符只能用在星期字段，该字段指定了第几个星期value在某月中
     **/
    //每1分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron() {
        log.debug("每分钟执行一次，注意观察日志时间");
    }

}
