package com.kjh.wiken.controller

import com.kjh.wiken.service.KenService
import com.kjh.wiken.vo.KenSourceInterpreter
import com.kjh.wiken.vo.Rq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UsrKenController(private val kenService: KenService) {
    @Autowired
    private lateinit var rq: Rq;

    // ken 작성/편집 화면 보여줌
    @RequestMapping("/ken")
    fun showWrite(): String {
        rq.setCurrentPageCanSaveKen(true)
        return "usr/ken/write"
    }

    // ken 작성/편집 화면 보여줌
    @RequestMapping("/ken/mine")
    fun showMyList(model: Model): String {
        val kens = kenService.getKensByMemberId(rq.getLoginedMemberId())

        model.addAttribute("kens", kens)

        return "usr/ken/my-list"
    }

    // ken 작성 처리
    @RequestMapping("/ken/doWrite")
    @ResponseBody
    fun doWrite(source: String, result: String): String {
        // 켄 소스 해석기 생성
        val kenSourceInterpreter = KenSourceInterpreter.from(source)
        // 해석기에서 제목 가져옴
        val title = kenSourceInterpreter.getTitle()

        val resultData = kenService.write(rq.getLoginedMemberId(), title, source, result)
        val id = resultData.getData()

        return rq.replaceJs("", "../ken/${id}/edit")
    }

    // ken 편집 처리
    @RequestMapping("/ken/doModify")
    @ResponseBody
    fun doModify(id: Int, source: String, result: String): String {
        val ken = kenService.getKen(id) ?: return rq.historyBackJs("존재하지 않는 ken 입니다.")

        if (ken.memberId != rq.getLoginedMemberId()) {
            return rq.historyBackJs("권한이 없습니다.")
        }

        var newSource = source

        // 켄 소스 해석기 생성
        var kenSourceInterpreter = KenSourceInterpreter.from(source)

        if (kenSourceInterpreter.hasConfig == false) {
            newSource = "$" + "$" + "config\n"
            newSource += ken.genKenConfigSource()
            newSource += "\n" + "$" + "$" + "\n"
            newSource += source.trim()

            kenSourceInterpreter = KenSourceInterpreter.from(newSource)
        }

        // 해석기에서 제목 가져옴
        val title = kenSourceInterpreter.getTitle()

        kenService.modify(id, title, newSource, result)

        return rq.replaceJs("", "../ken/${id}/edit")
    }

    // ken 삭제
    @RequestMapping("/ken/doDelete")
    @ResponseBody
    fun doDelete(id: Int): String {
        val ken = kenService.getKen(id) ?: return rq.historyBackJs("존재하지 않는 ken 입니다.")

        if (ken.memberId != rq.getLoginedMemberId()) {
            return rq.historyBackJs("권한이 없습니다.")
        }

        val resultData = kenService.delete(id)

        return rq.replaceJs(resultData.getMsg(), "../ken")
    }

    @RequestMapping("/ken/{id}/edit")
    fun showModify(@PathVariable("id") id: Int, model: Model): String {
        val ken = kenService.getKen(id) ?: return rq.historyBackJsOnTemplate("존재하지 않는 ken 입니다.")

        if (ken.memberId != rq.getLoginedMemberId()) {
            return rq.historyBackJsOnTemplate("권한이 없습니다.")
        }

        rq.currentPageCanDeleteCurrentKen = true
        rq.setCurrentPageCanGoViewCurrentKen(true)
        rq.setCurrentPageCanSaveKen(true)

        model.addAttribute("ken", ken)
        return "usr/ken/write"
    }

    @RequestMapping("/ken/{id}")
    fun showDetail(@PathVariable("id") id: Int, model: Model): String {
        rq.setCurrentPageCanGoEditCurrentKen(true)
        val ken = kenService.getKen(id)
        model.addAttribute("ken", ken)
        return "usr/ken/detail"
    }
}