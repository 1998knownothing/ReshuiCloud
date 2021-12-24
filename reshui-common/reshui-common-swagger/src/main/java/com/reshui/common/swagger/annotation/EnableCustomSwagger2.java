package com.reshui.common.swagger.annotation;


import com.reshui.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerAutoConfiguration.class })
//http://localhost:xxxx/swagger-ui/index.html#/
public @interface EnableCustomSwagger2
{

}
