package info.xiaomo.fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : xiaomo
 * Description: 文件上传下载启动器
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.fileupload")
public class FileUploadMain {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadMain.class, args);
    }

}
