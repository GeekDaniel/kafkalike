package top.dannystone.message.service;

import top.dannystone.message.AbstractMessageCenter;
import top.dannystone.message.Topic;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: daniel
 * Date: 2018-12-09
 * Time: 下午10:25
 */
public class TopicService {
    void createTopic(Topic topic) {
        AbstractMessageCenter.topics.add(topic);
    }
}
