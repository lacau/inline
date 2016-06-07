package br.com.inline;

import java.util.Map;

import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * Created by lacau on 09/05/16.
 */
@Component
@DependsOn("registerChains")
public final class ChainLocator {

    @Autowired
    private Map<String, ChainBase> chains;

    public final ChainBase getChain(String chainName) {
        return chains.get(chainName);
    }
}