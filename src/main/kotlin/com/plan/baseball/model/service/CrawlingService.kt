package com.plan.baseball.model.service

import org.jsoup.Jsoup

class CrawlingService(
    private var teamURL: String
) {
    fun loadBatterData(): MutableList<List<String>>{
        val resultList: MutableList<List<String>> = mutableListOf()
        val doc = Jsoup.connect(this.teamURL).get()
        val table = doc.select("table")

        val rowList = table.select("tr")
        for(row in rowList){
            val cellList = row.select("th, td")
            val itemList = cellList.map { it.text() }

            if(itemList.firstOrNull() != "순위"){
                resultList.add(itemList)
            }
        }
        return resultList
    }
}