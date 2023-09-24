//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfig {

    @Bean
    public ModelMapper modelMapper() { //เอาไว้ map DTO กับ DAO
        return new ModelMapper();
    }
}

