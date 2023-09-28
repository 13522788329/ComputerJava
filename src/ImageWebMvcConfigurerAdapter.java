import com.pla.Utils.LocalUploadImageUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public abstract class ImageWebMvcConfigurerAdapter implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("" + LocalUploadImageUtil.ConfigFileMessageUtils.IMAGE_DIR_NAME + "//**").addResourceLocations("file:" + LocalUploadImageUtil.ConfigFileMessageUtils.IMAGE_ALL_DIR);
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
