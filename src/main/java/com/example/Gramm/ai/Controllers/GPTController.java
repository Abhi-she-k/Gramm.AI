package com.example.Gramm.ai.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gramm.ai.API.GPTAPI;

@RestController
public class GPTController {
    private final GPTAPI gptService;

    public GPTController(GPTAPI gptService) {
        this.gptService = gptService;
    }

    @GetMapping("/ask")
    public String askGPT(@RequestParam String text , @RequestParam String type){

        if (type.equals("grammar")) {
            String prompt = "Gramatically edit the text below. DO NOT MENTION WHAT YOU ARE DOING, DO NOT CHANGE THE MEANING OF THE TEXT, ONLY RESPOND WITH THE GRAMATICALLY EDITED TEXT, REPHRASE THE TEXT IF NEEDED, USE PROPER ENGLISH. THANKS!  \n\n"
                    + text;
            String response = this.gptService.ask(prompt);
            System.out.println("Edited Text: " + response);
            return response;
        }
        else if (type.equals("paraphrase")) {
            String prompt = "Paraphrase the text below. DO NOT MENTION WHAT YOU ARE DOING, DO NOT CHANGE THE MEANING OF THE TEXT, ONLY RESPOND WITH THE PARAPHRASED TEXT, REPHRASE THE TEXT IF NEEDED, USE PROPER ENGLISH. THANKS!  \n\n"
                    + text;
            String response = this.gptService.ask(prompt);
            System.out.println("Paraphrased Text: " + response);
            return response;
        }
        else if (type.equals("pro")) {
            String prompt = "rewrite the text below more professionally. DO NOT MENTION WHAT YOU ARE DOING, DO NOT CHANGE THE MEANING OF THE TEXT, ONLY RESPOND WITH THE professionalized TEXT, REPHRASE THE TEXT IF NEEDED, USE PROPER ENGLISH. THANKS!  \n\n"
                    + text;
            String response = this.gptService.ask(prompt);
            System.out.println("Summarized Text: " + response);
            return response;
        }
        else if (type.equals("ask")) {
            String response = this.gptService.ask(text);
            System.out.println("Summarized Text: " + response);
            return response;
        }
        else {
            return "Invalid type";
        }
    }
}
