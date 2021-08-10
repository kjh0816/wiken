package com.kjh.wiken.service

import com.kjh.wiken.repository.BlogRepository
import com.kjh.wiken.vo.Ken
import org.springframework.stereotype.Service

@Service("blogService")
class BlogService(private val blogRepository: BlogRepository) {
    fun getArticlesByKenIds(kenIds: List<Int>): List<Ken> {
        return blogRepository.getArticlesByKenIds(kenIds)
    }
}
