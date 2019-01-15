package com.fct.core;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.stat.DruidStatService;
import com.alibaba.fastjson.JSONObject;

/**
 * 作者：starmoon1994
 * 来源：CSDN
 * 原文：https://blog.csdn.net/zzxx1994617/article/details/80549736
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 * @author xstarfct
 * @version 2019/1/11 5:51 PM
 */
@Component
public class CustomDruidStatLogger extends DruidDataSourceStatLoggerAdapter implements DruidDataSourceStatLogger {
    private static Logger myLogger = LoggerFactory.getLogger(CustomDruidStatLogger.class);

    /**
     * 获取DruidStatService
     */
    private DruidStatService druidStatService = DruidStatService.getInstance();

    /**
     * 是否是重启后的第一次记录
     */
    private boolean isFirstFlag = true;

//    @Autowired
//    private SyslogService syslogService;

    // 启动后延迟5秒调用  每5*60*1000即5分钟记录一次
//    @Scheduled(initialDelay = 5000, fixedDelay = 300000)
    @Scheduled(initialDelay = 5000, fixedDelay = 20000)
    // 定时任务异步化  还需在启动类上加@EnableAsync
    @Async
    public void log() throws InterruptedException {

        // 首次启动标志
        if (isFirstFlag) {
            myLogger.info("===============已重启，重启时间是{}，开始新的记录===============", LocalDateTime.now().toString());
            isFirstFlag = !isFirstFlag;
//            Syslog newLog = new Syslog();
//            newLog.setLogType("druidLog");
//            newLog.setLogBody("检测到重启");
//            newLog.setCreatTime(new Date());
//            syslogService.save(newLog);
        }

        JSONObject allResult = new JSONObject(16, true);

        // 首页信息
        String basicJson = druidStatService.service("/basic.json");
        // 数据源
        String datasourceJson = druidStatService.service("/datasource.json");
        // SQL监控
        String sqlJson = druidStatService.service("/sql.json?orderBy=SQL&orderType=desc&page=1&perPageCount=1000000&");
        // SQL防火墙
        String wallJson = druidStatService.service("/wall.json");
        // web应用
        String webappJson = druidStatService.service("/webapp.json");
        // URI监控
        String weburiJson = druidStatService.service("/weburi.json?orderBy=URI&orderType=desc&page=1&perPageCount=1000000&");
        // session监控
        String websessionJson = druidStatService.service("/websession.json");
        // spring监控
        String springJson = druidStatService.service("/spring.json");

        allResult.put("/basic.json", JSONObject.parseObject(basicJson));
        allResult.put("/datasource.json", JSONObject.parseObject(datasourceJson));
        allResult.put("/sql.json", JSONObject.parseObject(sqlJson));
        allResult.put("/wall.json", JSONObject.parseObject(wallJson));
        allResult.put("/webapp.json", JSONObject.parseObject(webappJson));
        allResult.put("/weburi.json", JSONObject.parseObject(weburiJson));
        allResult.put("/websession.json", JSONObject.parseObject(websessionJson));
        allResult.put("/spring.json", JSONObject.parseObject(springJson));

        String allResultJsonString = allResult.toJSONString();
        myLogger.info("Druid监控定时记录，allResult==={}", allResultJsonString);

//        Syslog newLog = new Syslog();
//        newLog.setLogType("druidLog");
//        newLog.setLogBody(allResultJsonString);
//        newLog.setCreatTime(new Date());
//        syslogService.save(newLog);
    }

}
