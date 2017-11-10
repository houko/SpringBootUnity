package info.xiaomo.crawler.schedule;

import info.xiaomo.core.untils.DownUtil;
import info.xiaomo.crawler.model.ShikigamiModel;
import info.xiaomo.crawler.service.ShikigamaService;
import info.xiaomo.crawler.spider.OnnmyoujiSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:59
 */
@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private final ShikigamaService shikigamaService;

    @Autowired
    public ScheduledTasks(ShikigamaService shikigamaService) {
        this.shikigamaService = shikigamaService;
    }


    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime() {
        System.out.println("Scheduling Tasks Examples: The time is now " + dateFormat().format(new Date()));
    }

    //每1分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron() {
        LOGGER.debug("开始执行任务：");
        List<ShikigamiModel> shikigamiModel = OnnmyoujiSpider.getShikigamiModel();
        for (ShikigamiModel model : shikigamiModel) {
            shikigamaService.save(model);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void downImage() throws Exception {
        LOGGER.debug("开始执行任务：");
        List<ShikigamiModel> shikigamiModel = shikigamaService.findAll();
        for (ShikigamiModel aShikigamiModel : shikigamiModel) {
            String url = aShikigamiModel.getImage();
            DownUtil.download(url, "D:\\yys\\");
            LOGGER.debug("开始下载图片:{}", url);
        }
    }


    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

}
