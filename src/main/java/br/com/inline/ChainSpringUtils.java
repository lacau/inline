package br.com.inline;

import java.util.LinkedList;
import java.util.List;

import br.com.inline.annotation.Chain;
import br.com.inline.annotation.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by lacau on 05/05/16.
 */
@Component
public class ChainSpringUtils {

    @Autowired
    public ConfigurableListableBeanFactory applicationContext;

    public <T> T registerBean(String qualifier, T bean) {
        applicationContext.registerSingleton(qualifier, bean);
        return bean;
    }

    public ChainBase getChain(Chain chain) {
        List list = new LinkedList();

        for(Class clazz : chain.commands()) {
            list.add(getBean(clazz));
        }

        return new ChainBase(list);
    }

    private <T> T getBean(Class<T> clazz) {
        Command command = clazz.getAnnotation(Command.class);
        if(command != null && !StringUtils.isEmpty(command.value())) {
            return (T) applicationContext.getBean(command.value());
        }

        String className = clazz.getSimpleName();

        if(className.length() > 1 && Character.isUpperCase(className.charAt(1))) {
            return (T) applicationContext.getBean(className);
        }

        return (T) applicationContext.getBean(className.substring(0, 1).toLowerCase() + className.substring(1));
    }
}