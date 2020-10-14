package com.eknown.elasticsearchdemo;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/10 16:50
 */
@RestController
@RequestMapping("article")
public class ArticleAction {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("{id}")
    public JsonResult findById(@PathVariable String id) {
        Optional<Article> article = articleRepository.findById(id);
        JsonResult jsonResult = new JsonResult(true);
        jsonResult.put("article", article.orElse(null));
        return jsonResult;
    }

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @GetMapping("list")
    public JsonResult list(Integer currentPage, Integer limit) {
        if (currentPage == null || currentPage < 0 || limit == null || limit <= 0) {
            return new JsonResult(false, "请输入合法的分页参数");
        }
        // 分页列表查询
        // 旧版本的 Repository 中的 search 方法被废弃了。
        // 这里采用 ElasticSearchRestTemplate 或 ElasticsearchOperations 来进行分页查询

        JsonResult jsonResult = new JsonResult(true);
        NativeSearchQuery query = new NativeSearchQuery(new BoolQueryBuilder());
        query.setPageable(PageRequest.of(currentPage, limit));

        // 方法1：
        SearchHits<Article> searchHits = elasticsearchRestTemplate.search(query, Article.class);

        // 方法2：
        // SearchHits<Article> searchHits = elasticsearchOperations.search(query, Article.class);

        // 方法3：
        // 在方法上加 @Query 注解，其中手写查询语句 —— 有点难度，不会写

        List<Article> articles = searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
        jsonResult.put("count", searchHits.getTotalHits());
        jsonResult.put("articles", articles);
        return jsonResult;
    }

    @PostMapping("")
    public JsonResult save(Article article) {
        // 新增或更新
        String verifyRes = verifySaveForm(article);
        if (!StringUtils.isEmpty(verifyRes)) {
            return new JsonResult(false, verifyRes);
        }

        if (StringUtils.isEmpty(article.getId())) {
            article.setCreateTime(new Date());
        }

        Article a = articleRepository.save(article);
        boolean res = a.getId() != null;
        return new JsonResult(res, res ? "保存成功" : "");
    }

    @DeleteMapping("{id}")
    public JsonResult delete(@PathVariable String id) {
        // 根据 id 删除
        articleRepository.deleteById(id);
        return new JsonResult(true, "删除成功");
    }


    private String verifySaveForm(Article article) {
        if (article == null || StringUtils.isEmpty(article.getTitle())) {
            return "标题不能为空";
        } else if (StringUtils.isEmpty(article.getContent())) {
            return "内容不能为空";
        }

        return null;
    }
}
