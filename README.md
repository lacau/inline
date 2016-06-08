# inline
## What it is
**inline** is a tiny java api/framework that helps you to use the ***chain of responsibility pattern*** through **annotations**.
It sits on top of **apache commons chain**.
## Config
You just need to add **"br.com.inline"** package to your **spring** package scan.
## Code example
#### *Chain definition*
Define the chain and its commands. **Should** be annotated it with **@Chain** passing its command classes into **commands** parameter.
```java
@Chain(qualifier = "chain1", commands = {
        Command1.class,
        Command2.class
})
public final class Chain1 {
}
```
