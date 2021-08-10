package com.kjh.wiken.controller

import com.kjh.wiken.service.ArticleService
import com.kjh.wiken.vo.Article
import com.kjh.wiken.vo.Rq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UsrArticleController(private val articleService: ArticleService) {
    @Autowired
    private lateinit var rq: Rq;

    @RequestMapping("/article/getArticles")
    @ResponseBody
    fun getArticles(): List<Article> {
        return articleService.getArticles()
    }

    @RequestMapping("/article/list")
    fun showList(model: Model): String {
        val articles = articleService.getArticles()

        model.set("articles", articles)

        return "usr/article/list"
    }
}