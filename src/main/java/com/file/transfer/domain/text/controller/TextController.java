package com.file.transfer.domain.text.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.file.transfer.domain.text.dto.TextSaveDto;
import com.file.transfer.domain.text.service.TextService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TextController {
	
	private final TextService textService;
	
	@PostMapping("/save/text")
    public String save(TextSaveDto text) {
		textService.Save(text);
		return "redirect:/";
    }

}
