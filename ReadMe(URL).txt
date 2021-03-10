项目本体：West2_Round4
数据库文件：west2
云盘结构：
   
                                                                                                                                 1. 转注册页面
登陆页面(能够执行登录功能、验证账号是否存在以及账号密码是否匹配)- 2. 登陆成功-->根据用户身份（管理员或普通用户）进行跳转
                                                                                                                                 3. 登陆失败 再次登录

注册页面-->判断注册信息（1.昵称 2.密码 3.验证密码）是否符合规则
                                        密码和验证密码需要相同
                                        密码长度需要超过8
                                        用户昵称需要小于等于5
                                        昵称未被占用

注册成功-->跳转欢迎页面-->显示账号信息

用户页面--->文件结构
                --->文件查看页面

管理员页面-->查看未审核文件、删除用户、删除不合格文件、通过合格文件



URL:
1.从登陆页面：
跳转到登陆页面：http://localhost:8080/（初始页面）
执行登录功能：http://localhost:8080/login/doLogin
跳转到注册页面：http://localhost:8080/login/goRegister

2.从注册页面：
跳转到注册页面：http://localhost:8080/login/goRegister
跳转到登陆页面：http://localhost:8080/register/goLogin
执行注册功能：http://localhost:8080/register/doRegister

3.用户页面：
跳转回登陆页面：http://localhost:8080/user/goLogin
在指定路径创建文件夹：http://localhost:8080/user/createFolder
向指定路径上传文件：http://localhost:8080/user/uploadFiles
删除指定路径上的文件：http://localhost:8080/user/deleteFile
从指定路径下载文件：http://localhost:8080/user/download

4.用户图片查看页面
从指定路径下载文件：http://localhost:8080/lookPicture/download
删除指定路径上的文件：http://localhost:8080/lookPicture/deleteFile
跳转回用户页面：http://localhost:8080/lookPicture/goUser

5.管理员页面
跳转回登陆页面：http://localhost:8080/administrator/goLogin
显示未通过文件：http://localhost:8080/administrator/showUncheckedFiles
通过合格文件：http://localhost:8080/administrator/CheckFile
删除不合格文件：http://localhost:8080/administrator/delUncheckedFile
删除用户：http://localhost:8080/administrator/delAccount



