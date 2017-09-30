package com.cui.blog.web.task;

import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.biz.service.ArticleService;
import com.cui.blog.web.filter.ArticleViewCountFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 文章访问数量写入数据库定时任务
 * <p>
 * Created by cuishixiang on 2017-09-29.
 */
@Component
public class ArticleViewCountTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleViewCountTask.class);

    @Resource
    private ArticleService articleService;

    /**
     * 定时将文章访问数量写入数据库
     */
    @Scheduled(cron = "${ArticleViewCount_task}")
    public void writeArticleViewCountCache2DB() {
        for (Map.Entry<Integer, Integer> entry : ArticleViewCountFilter.idAndCountMap.entrySet()) {
            try {
                if (entry.getValue() > 0) {
                    articleService.updateViewCount(entry.getKey(), entry.getValue());
                    entry.setValue(0);
                }
            } catch (BlogException e) {
                LOGGER.error("<web><ArticleViewCountTask><writeArticleViewCountCache2DB><><>文章浏览量更新失败：", e);
            }
        }
    }
}
