1.授权码模式
1.1  http://localhost:9090/oauth/authorize?response_type=code&client_id=client_3&redirect_uri=http://www.baidu.com

1.2  http://localhost:9090/oauth/token?grant_type=authorization_code&code=ku9KmV&redirect_uri=http://www.baidu.com&scope=all&client_id=client_3&client_secret=123456

2.密码模式
http://localhost:9090/oauth/token?username=admin&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456