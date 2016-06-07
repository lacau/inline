package br.com.inline.command;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.Test;

/**
 * Created by lacau on 13/05/16.
 */
public class AbstractCommandTest {

    @Test
    public void testConstructorWhenSuccess() throws Exception {
        new CommandFake1().execute((Context) new ContextFake());
    }

    @Test(expected = IllegalStateException.class)
    public void testConstructorWhenFail() {
        new CommandFake2();
    }
}

class CommandFake1 extends AbstractCommand<ContextFake> {

    @Override
    public boolean execute(ContextFake context) throws Exception {
        return false;
    }
}

class CommandFake2 extends AbstractCommand {

    @Override
    public boolean execute(ContextBase context) throws Exception {
        return false;
    }
}

class ContextFake extends ContextBase {
}