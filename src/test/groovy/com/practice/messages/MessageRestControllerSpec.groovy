package com.practice.messages

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.messages.controller.MessageRestController
import com.practice.messages.model.Message
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class MessageRestControllerSpec extends Specification {

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MessageRestController()).build()

    def "[/messages][GET] メッセージリストをJSONで取得できる"() {
        expect:
        mockMvc.perform(get('/messages'))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    }

    def "[/messages][POST] JSONで指定した内容で新規メッセージを作成できる"() {
        setup:
        String jsonStr = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(new Message(text: 'hogehoge'))

        expect:
        mockMvc.perform(post('/messages')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr.getBytes()))
                .andExpect(status().is(HttpStatus.CREATED.value()))
    }
}
