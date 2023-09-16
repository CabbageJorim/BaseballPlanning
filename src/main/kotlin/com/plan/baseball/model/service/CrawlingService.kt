//TODO: Make CSV after Crawling
//TODO: Using JSoup

package com.plan.baseball.model.service

import org.jsoup.Jsoup
import java.io.BufferedWriter
import java.io.FileWriter
import java.lang.StringBuilder

class CrawlingService(
    private var teamURL: String
) {
    fun create(){
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
                (itemList[0] == "순위") -> continue
                else -> {
                    println(itemList)
                    println("line lastIndex: ${itemList.lastIndex}")
                    println("line size: ${itemList.size}" )
                }
            }
        }

    }
}