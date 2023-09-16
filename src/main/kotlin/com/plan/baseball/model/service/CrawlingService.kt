//TODO: Make CSV after Crawling
//TODO: Using JSoup

package com.plan.baseball.model.service

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class CrawlingService(
    private var teamURL: String
) {
    private fun getTrsInTable(tableList: List<Element>): List<Element> {
        var trList:List<Element> = emptyList()
        for(table in tableList){
            trList = table.select("tr")
        }
        return trList
    }

    fun crawlingTeamTableOptions():String{
        var result = ""
        try{
            val doc = Jsoup.connect(this.teamURL).get()
            val tableList: List<Element> = doc.select("table")
            for(tr in getTrsInTable(tableList)){
                result += tr.select("td").text()
            }
        }catch (_:Exception){

        }.toString()

        return result
    }
}