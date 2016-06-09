package br.com.inline;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.inline.annotation.Chain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lacau on 05/05/16.
 */
@Configuration
public class ChainSpringRegister {

    @Autowired
    private ApplicationContext context;

    @Bean
    public Map<String, Command> registerCommands() throws IllegalAccessException, InstantiationException {
        final Map<String, Command> commands = new HashMap<>();
        final Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(br.com.inline.annotation.Command.class);
        for(String beanName : beansWithAnnotation.keySet()) {
            final Object command = beansWithAnnotation.get(beanName);
            commands.put(beanName, (Command) command);
        }

        return commands;
    }

    @Bean(name = "chains")
    public Map<String, ChainBase> chains(Map<String, Command> commands) {
        final Map<String, ChainBase> chains = new HashMap<>();

        final Set<Class<?>> annotatedWith = new Reflections().getTypesAnnotatedWith(Chain.class);
        for(Class<?> clazz : annotatedWith) {
            Chain chain = clazz.getAnnotation(Chain.class);
            chains.put(chain.qualifier(), getChain(chain, commands));
        }

        return chains;
    }

    private ChainBase getChain(Chain chain, Map<String, Command> commands) {
        final List list = new LinkedList();

        for(Class clazz : chain.commands()) {
            list.add(commands.get(getClassName(clazz)));
        }

        return new ChainBase(list);
    }

    private String getClassName(Class clazz) {
        final String className = clazz.getSimpleName();

        if(className.length() > 1 && Character.isUpperCase(className.charAt(1))) {
            return className;
        }

        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }
}