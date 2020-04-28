package com.fykj.scaffold.support.conns;

/**
 * 常量类
 *
 * @author zhangzhi
 */
public abstract class Cons {

    /**
     * 角色编码-系统管理员
     */
    public static final String ROLE_CODE_ADMIN = "SYS_ADMIN";


    /**
     * 角色编码-测试账号角色
     */
    public static final String ROLE_CODE_TEST_ACCOUNT = "test_account_role";

    public static final String TEST_USER = "sut_test_account";

    /**
     * 资源类型--菜单
     */
    public static final Integer RESOURCE_TYPE_MENU = 1;

    /**
     * 资源类型--按钮
     */
    public static final Integer RESOURCE_TYPE_BUTTON = 2;

    /**
     * 重置初始密码
     */
    public static final String INIT_PSD = "123456";

    /**
     * 默认排序字段
     */
    public static final String SORT_COLUMN_DEFAULT = "createDate";

    /**
     * 默认排序方向
     */
    public static final String SORT_ORDER_DEFAULT = "desc";

    /**
     * 默认日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认日期时间格式
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 文件存储方式code
     */
    public static final String SERVER_CODE = "server-code";

    /**
     * 文件类型code
     */
    public static final String FILE_TYPE = "fileType";

    /**
     * 文件类型-图片 code
     */
    public static final String FILE_TYPE_IMAGE = "picture";

    /**
     * 未知文件格式
     */
    public static final String OTHER_TYPES = "otherTypes";

    /**
     * 审核通过
     */
    public static final String AUDIT_STATUS_PASS = "pass";

    /**
     * 审核不通过
     */
    public static final String AUDIT_STATUS_NOT_PASS = "notPass";

    /**
     * 待审核
     */
    public static final String AUDIT_STATUS_CHECK = "CheckPending";


    private Cons() {
        throw new IllegalStateException("Constants class");
    }

    /**
     * pid（第三方接口加密参数）
     */
    public static final String PID = "10000";

    /**
     * format（第三方接口加密参数）
     */
    public static final String FORMAT = "json";

    /**
     * authcode(授权码，第三方接口加密参数）
     */
    public static final String AUTHCODE = "123456";
}
