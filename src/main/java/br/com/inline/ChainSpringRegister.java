package br.com.inline;

import java.util.Set;

import br.com.inline.annotation.Chain;
import org.apache.commons.chain.impl.ChainBase;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lacau on 05/05/16.
 */
@Configuration
public class ChainSpringRegister {

    @Bean
    public String registerChains(ChainSpringUtils chainSpringUtils) {
        Set<Class<?>> annotatedWith = new Reflections().getTypesAnnotatedWith(Chain.class);

        for(Class<?> clazz : annotatedWith) {
            Chain chain = clazz.getAnnotation(Chain.class);
            ChainBase chainBase = chainSpringUtils.getChain(chain);
            chainSpringUtils.registerBean(chain.qualifier(), chainBase);
        }

        return "";
    }
}