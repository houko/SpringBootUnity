package info.xiaomo.actuator.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 自定义健康检查。Bean 名字去掉 HealthIndicator 后缀就是它在 /actuator/health 里的键,
 * 所以这里会显示为 "diskSpaceRatio"。
 *
 * @author : xiaomo
 */
@Component
public class DiskSpaceRatioHealthIndicator implements HealthIndicator {

    /**
     * 可用空间低于该比例就判定为不健康, 可通过配置覆盖(测试里把它设成 0 以消除对磁盘的依赖)。
     */
    private final double threshold;

    public DiskSpaceRatioHealthIndicator(
            @Value("${actuator.disk-space-ratio.threshold:0.05}") double threshold) {
        this.threshold = threshold;
    }

    @Override
    public Health health() {
        File root = new File(".");
        long total = root.getTotalSpace();
        long free = root.getUsableSpace();
        if (total <= 0) {
            return Health.unknown().withDetail("reason", "无法读取磁盘信息").build();
        }

        double freeRatio = (double) free / total;
        Health.Builder builder = freeRatio >= threshold ? Health.up() : Health.down();
        return builder
                .withDetail("totalBytes", total)
                .withDetail("freeBytes", free)
                .withDetail("freeRatio", String.format("%.4f", freeRatio))
                .withDetail("threshold", threshold)
                .build();
    }

}
