# inline
## What it is
**inline** is a tiny java api/framework that helps you to use the ***chain of responsibility pattern*** through **annotations**.
It sits on top of **apache commons chain**.
## Config
You just need to add **"br.com.inline"** package to your **spring** package scan.
## Code example
#### *Chain definition*
* Define the chain and its commands.
* **Should** be annotated it with **@Chain** from **br.com.uol.inline.annotation** package passing its command classes into **commands** parameter.
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
* Should be annotated with **@Command** from **br.com.uol.inline.annotation** package.
* Should extends **AbstractCommand** from **br.com.uol.inline.command** package passing a class that extends ContextBase.
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
