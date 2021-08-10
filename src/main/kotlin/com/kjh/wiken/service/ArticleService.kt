package com.kjh.wiken.service

import com.kjh.wiken.repository.ArticleRepository
import com.kjh.wiken.vo.Article
import org.springframework.stereotype.Service

@Service("articleService")
class ArticleService(private val articleRepository: ArticleRepository) {
    fun getArticles(): List<Article> {
        return articleRepository.getArticles()
    }
}
