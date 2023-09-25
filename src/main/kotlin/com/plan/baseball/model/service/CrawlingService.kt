package com.plan.baseball.model.service

import org.jsoup.Jsoup
import java.lang.StringBuilder

class CrawlingService(
    private var teamURL: String
) {
    fun loadBatterData(): MutableList<List<String>>{
        val resultList: MutableList<List<String>> = mutableListOf()
        val doc = Jsoup.connect(this.teamURL).get()
        val table = doc.select("table")

        val rowList = table.select("tr")
        for (row in rowList){
            val cellList = row.select("th") + row.select("td")
            val csvLine = StringBuilder()

            for(cell in cellList){
                csvLine.append(cell.text()).append(",")
            }
            csvLine.deleteCharAt(csvLine.length - 1)
            val itemList = csvLine.split(",")

            when{
                (itemList[0] == "순위") -> {
                    continue
                }
                else -> {
                    resultList += itemList
                }
            }
        }
        return resultList
    }
}