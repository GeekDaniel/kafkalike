package top.dannystone.cors.server;

import top.dannystone.exception.NodeConfigException;
import top.dannystone.message.MessageCloseableIterator;
import top.dannystone.message.NodeConfig;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: daniel
 * Date: 2018-11-06
 * Time: 下午11:36
 */
public abstract class AbstractMessageServer {
    Pattern IP_PATTERN=Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

    public MessageCloseableIterator getMessageCloseableIterator() {
        if(messageCloseableIterator==null){
            throw new RuntimeException("服务端未初始化！");
        }
        return messageCloseableIterator;
    }

    private MessageCloseableIterator messageCloseableIterator=null;

    boolean validateNodeConfig(List<NodeConfig> nodeConfigs ){
        return !nodeConfigs.stream().anyMatch(e->!checkValid(e));
    }

    boolean checkValid(NodeConfig nodeConfig){
        Matcher matcher=IP_PATTERN.matcher(nodeConfig.getIp());
        int port = nodeConfig.getPort();
        if(matcher.find()&&port>1&&port<65535){
            return true;
        }
        return  false;
    }

    /**
     * server初始化 方法
     * @param nodeConfigs
     */
    void boot(List<NodeConfig> nodeConfigs){
        if(!validateNodeConfig(nodeConfigs)){
            throw new NodeConfigException("节点配置信息错误！");
        }
        doBoot(nodeConfigs);
    }

    /**
     * server 初始化实现
     * @param nodeConfigs
     */
    protected abstract  void  doBoot(List<NodeConfig> nodeConfigs);


}