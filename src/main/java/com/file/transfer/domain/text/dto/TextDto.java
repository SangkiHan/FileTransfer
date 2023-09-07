package com.file.transfer.domain.text.dto;

import com.file.transfer.domain.text.entity.Text;

import lombok.Data;

@Data
public class TextDto {
    private Long id;
    private String text;
    
	public TextDto(Text text) {
		this.id = (text==null) ? 0 :text.getId();
		this.text = (text==null) ? "" :text.getText();
	}

	public TextDto(Long id, String text) {
		this.id = id;
		this.text = text;
	}
}
