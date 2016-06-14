# inline
## What it is
**inline** is a tiny java api/framework that helps you to use the ***chain of responsibility pattern*** through **annotations**.
It sits on top of **apache commons chain**.
## Config
You just need to add **"br.com.inline"** package to your **spring** package scan.
## Code example
#### *Chain definition*
* Define the chain and its commands.
* **Should** be annotated it with **@Chain** from **br.com.inline.annotation** package passing its command classes into **commands** parameter.
```java
@Chain(qualifier = "chain1", commands = {
        Command1.class,
        Command2.class
})
public final class Chain1 {
}
```
#### *Context definition*
* Define context used across chain commands.
* **Should** extends **ContextBase** from **org.apache.commons.chain.impl** package.
```java
public class Context1 extends ContextBase {
    // Declare any attributes you need across commands
}
```
#### *Commands definition*
* Define chain's commands.
* Should be annotated with **@Command** from **br.com.inline.annotation** package.
* Should extends **AbstractCommand** from **br.com.inline.command** package passing a class that extends ContextBase.
* Return **true** from execute method if you want to **interrupt** the flow, **false** to proceed.
```java
@Command
public class Command1 extends AbstractCommand<Context1> {

    @Override
    protected boolean execute(Context1 context) throws Exception {
        return false;
    }
}
```
#### *Chain start*
* Autowired **ChainLocator** from **br.com.inline** package into your spring component.
* Use ChainLocator to get your chain by the **qualifier** attribute defined on **@Chain** annotation.
```java
@Service
public class ChainService {

    @Autowired
    private ChainLocator chainLocator;
    
    public final void startChain() {
        final Context1 context = new Context1();
        final ChainBase chain = chainLocator.getChain("chain1");
        try {
            chain.execute(context);
        } catch(Exception e /*Any exceptions that your commands may throw*/) {
        
        }
    }
}
```
