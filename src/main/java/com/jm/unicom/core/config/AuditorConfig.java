package com.jm.unicom.core.config;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * AuditorConfig
 *
 * @author Eric
 * @date 2017/12/27
 */
@Configuration
public class AuditorConfig implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
    }
}
