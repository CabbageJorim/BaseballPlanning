//TODO: Make CSV after Crawling
//TODO: Using JSoup

package com.plan.baseball.model.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

class CrawlingService(
    private var teamURL: String
) {
    /**
     * 사용안 할 가능성 높은 메서드
     */
    fun crawlingTeam(): String{
        return try{
            val doc: Document = Jsoup.connect(this.teamURL).get()
            val tables: List<Element> = doc.select("table")
            tables.joinToString(separator = "") {
                table -> table.select(".fixed").toString()
            }
        }catch (e:Exception){
            "none"
        }finally {
            println("Crawling Fin")
        }
    }

}