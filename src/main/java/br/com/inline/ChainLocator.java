package br.com.inline;

import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.chain.impl.ChainBase;
import org.springframework.stereotype.Component;

/**
 * Created by lacau on 09/05/16.
 */
@Component
public final class ChainLocator {

    @Resource(name = "chains")
    private Map<String, ChainBase> chains;

    public final ChainBase getChain(String chainName) {
        return chains.get(chainName);
    }
}