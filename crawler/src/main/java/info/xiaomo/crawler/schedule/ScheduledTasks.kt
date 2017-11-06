package info.xiaomo.crawler.schedule

import info.xiaomo.core.untils.DownUtil
import info.xiaomo.crawler.service.ShikigamaService
import info.xiaomo.crawler.spider.OnnmyoujiSpider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @created : 2016/12/24 15:59
 */
@Component
class ScheduledTasks @Autowired
constructor(private val shikigamaService: ShikigamaService) {


    @Scheduled(fixedRate = (1000 * 30).toLong())
    fun reportCurrentTime() {
        println("Scheduling Tasks Examples: The time is now " + dateFormat().format(Date()))
    }

    //每1分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    fun reportCurrentByCron() {
        LOGGER.debug("开始执行任务：")
        val shikigamiModel = OnnmyoujiSpider.shikigamiModel
        for (model in shikigamiModel) {
            shikigamaService.save(model)
        }
    }

    @Scheduled(fixedRate = 1000)
    @Throws(Exception::class)
    fun downImage() {
        LOGGER.debug("开始执行任务：")
        val shikigamiModel = shikigamaService.findAll()
        for (aShikigamiModel in shikigamiModel) {
            val url = aShikigamiModel.image
            DownUtil.download(url!!, "D:\\yys\\")
            LOGGER.debug("开始下载图片:{}", url)
        }
    }


    private fun dateFormat(): SimpleDateFormat {
        return SimpleDateFormat("HH:mm:ss")
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(ScheduledTasks::class.java)
    }

}
