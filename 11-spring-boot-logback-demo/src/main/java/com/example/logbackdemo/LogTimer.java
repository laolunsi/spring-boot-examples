package com.example.logbackdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日志生成定时器
 * 注：生成一些让你摸不着头脑的示例日志
 * @author zfh
 * @version 1.0
 * @since 2019/12/7 14:00
 */
@Component
public class LogTimer {

    private static Logger logger = LoggerFactory.getLogger(LogTimer.class);

    /**
     * 每隔两分钟启动一次定时器，生成一些日志
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void createLogs() {
        logger.info("本次定时器启动时间：" + new Date().toLocaleString());
        logger.warn("定时器怎么一直在启动呢？");
        logger.debug("尝试关闭一下定时器看看？---实际上并没有编写这个功能");
        logger.error("定时器关闭失败了！！！");
    }

}
