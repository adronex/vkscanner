package vkscanner

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.client.TransportClient



/**
 * Created on 17.01.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */

@RestController
class VkApiWhore {

    data class WhoreDto(val items: List<ItemDto> = listOf<ItemDto>()) {
        //Whore Deserializer
        class Deserializer : ResponseDeserializable<WhoreDto> {
            override fun deserialize(content: String) = Gson().fromJson(content, WhoreDto::class.java)
        }
    }

    data class ItemDto(val id: String = "",
                       val text: String = "") {
        class Deserializer : ResponseDeserializable<ItemDto> {
            override fun deserialize(content: String) = Gson().fromJson(content, ItemDto::class.java)
        }
    }

    @RequestMapping(
            path = arrayOf("/vk"),
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun nameRequest(): WhoreDto? {

        val transportClient = HttpTransportClient()
        val vk = VkApiClient(transportClient)
        val response = vk.wall().search().count(5).ownerId(-24983798).query("oh, sleeper").execute()

        response.items.forEach { println(it.text) }

//        val url = "https://api.vk.com/method/wall.search"
//        val params = listOf(
//                "owner_id" to "-24983798",
//                "query" to "oh, sleeper",
//                "count" to "2",
//                "version" to "5.62")
//        val (request, response, result) = Fuel.get(url, params).responseObject(WhoreDto.Deserializer())
//        val (lola, bola, dola) = Fuel.get(url, params).responseString()
//        val (whore, error) = result
//        println(dola)
        return WhoreDto();
    }

}