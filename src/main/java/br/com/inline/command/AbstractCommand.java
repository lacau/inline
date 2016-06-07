package br.com.inline.command;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;

/**
 * Created by lacau on 25/05/16.
 */
public abstract class AbstractCommand<T extends ContextBase> implements Command {

    protected AbstractCommand() {
        final Type genericSuperclass = getClass().getGenericSuperclass();

        try {
            Type genericType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } catch(ClassCastException e) {
            throw new IllegalStateException("Class " + getClass() + " does not specify a generic that extends ContextBase class.");
        }
    }

    @Override
    public boolean execute(Context context) throws Exception {
        return execute((T) context);
    }

    protected abstract boolean execute(final T context) throws Exception;
}