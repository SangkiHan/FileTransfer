package com.file.transfer.global.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @description BasicEntity에서 @CreatedBy @LastModifiedBy의 자동으로 기입되게 하는 Audit설정
 * @author skhan
 * */
@Configuration
public class AuditConfig implements AuditorAware<String>{
	@Override
	public Optional<String> getCurrentAuditor() {
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = httpServletRequest.getRemoteAddr();
		}
		return Optional.of(ip);
	}
}