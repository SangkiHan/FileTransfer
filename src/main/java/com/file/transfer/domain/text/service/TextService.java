package com.file.transfer.domain.text.service;

import org.springframework.stereotype.Service;

import com.file.transfer.domain.text.dto.TextDto;
import com.file.transfer.domain.text.dto.TextSaveDto;
import com.file.transfer.domain.text.entity.Text;
import com.file.transfer.domain.text.repository.TextRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TextService {
	
	private final TextRepository textRepository;
	
	public TextDto findText() {
		return textRepository.findText();	
	}
	
	public void Save(TextSaveDto textValue) {
		if(textValue.getId()!=null) {
			Text text = textRepository.findById(textValue.getId()).orElse(null);
			text.setText(textValue.getText());
		}
		else {
			Text text = new Text();
			text.setTexts(textValue);
			textRepository.save(text);
		}
	}

}
