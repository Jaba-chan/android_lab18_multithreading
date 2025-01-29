package ru.evgenykuzakov.lab18

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.net.URL
import java.nio.charset.Charset

object Common {
    var currencies = mutableListOf<CurrencyToRuble>()

    fun initData() {
        val xml = URL("https://www.cbr.ru/scripts/XML_daily.asp")
            .readText(Charset.forName("Windows-1251"))

        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(StringReader(xml))

        var eventType = parser.eventType
        var currencyToRuble: CurrencyToRuble? = null
        var currentTag: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            val tagName = parser.name

            when (eventType) {
                XmlPullParser.START_TAG -> {
                    if (tagName == "Valute") {
                        currencyToRuble = CurrencyToRuble("", "")
                    } else if (currencyToRuble != null) {
                        currentTag = tagName
                    }
                }

                XmlPullParser.TEXT -> {
                    currencyToRuble?.let {
                        when (currentTag) {
                            "Name" -> it.name = parser.text
                            "Value" -> it.value = parser.text
                        }
                    }
                }

                XmlPullParser.END_TAG -> {
                    if (tagName == "Valute") {
                        currencyToRuble?.let { currencies.add(it) }
                    }
                }
            }
            eventType = parser.next()
        }
    }
}