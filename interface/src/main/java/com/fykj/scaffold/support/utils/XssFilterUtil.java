package com.fykj.scaffold.support.utils;

import com.fykj.scaffold.security.business.service.IXssTagAttributeProtocolService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author ZC
 * @date: 2019/10/29
 */
@Component
public class XssFilterUtil {

    /**
     * whitelist 里有 ：
     * tag(标签)，attribute_key(标签)，attribute_value(属性)，attribute_value(属性值),protocol(协议)
     * 四种粒度的白名单属性
     * tag:比如设置 img 标签，<img src="www.baidu.com"> 只会保留 <img>
     *
     * attribute_key: 需要设置 tag 和 attribute_key, 比如设置 img，src; <img src="www.baidu.com"> 会完全保留
     *
     * attribute_value：需要设置 tag，attribute_key，attribute_value, 比如设置：img, src, www.baidu.com;
     *                  任何img标签（如：<img alt="无图">）都会变为<img src="www.baidu.com">
     *
     * protocol: 不同于上述标签，设置protocol不仅要设置 tag,attribute_key,protocol,
     *            还需要单独设置tag,attribute_key,比如设置：img,src 和 img,src,https;
     *            <img src="www.baidu.com">会变成<img>，<img src="https:XXX">会完全保留
     *
     */
    private static final Whitelist whitelist = new Whitelist();

    static{
        initWhiteList();
    }

    /** 配置过滤化参数,不对代码进行格式化 */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    public static void init(){
        initWhiteList();
    }

    private static void initWhiteList() {
        IXssTagAttributeProtocolService xssTagAttributeService = SpringContextUtil.getBean(IXssTagAttributeProtocolService.class);
        //tag数组
        String[] tags = xssTagAttributeService.getTags();
        //tag和attribute_key
        Map<String, List<String>> tagAndAttrKeyMap = xssTagAttributeService.getTagAndAttrKey();
        //tag,attribute_key,attribute_value
        Map<String, Map<String, String>> attributesMap = xssTagAttributeService.getTagAndAttrKeyVal();
        //tag,attribute_key,protocol
        Map<String, Map<String, List<String>>> protocolMap = xssTagAttributeService.getTagAndKeyAndProtocol();
        //加入tags
        whitelist.addTags(tags);
        //加入tag和attribute_key
        for (Map.Entry<String, List<String>> entry : tagAndAttrKeyMap.entrySet()) {
            whitelist.addAttributes(entry.getKey(), entry.getValue().toArray(new String[entry.getValue().size()]));
        }
        //tag,attribute_key,attribute_value
        for (Map.Entry<String, Map<String, String>> entry : attributesMap.entrySet()) {
            for (Map.Entry<String, String> innerEntry : entry.getValue().entrySet()) {
                whitelist.addEnforcedAttribute(entry.getKey(), innerEntry.getKey(),
                        innerEntry.getValue());
            }
        }
        //加入tag,attribute_key,protocol
        for (Map.Entry<String, Map<String, List<String>>> entry : protocolMap.entrySet()) {
            for (Map.Entry<String, List<String>> innerEntry : entry.getValue().entrySet()) {
                whitelist.addProtocols(entry.getKey(), innerEntry.getKey(),
                        innerEntry.getValue().toArray(new String[innerEntry.getValue().size()]));
            }
        }
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
}
