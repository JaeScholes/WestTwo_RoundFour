var data = {
    username: '',
    tip: '',
    rtip:'',
    pwd: '',
    showPwd: false,
    rpwd: '',
    rshowPwd: false
}
new Vue({
    el: '#container',
    data: data,
    methods: {
        checkName: function () {
            if (this.username == '') {
                this.tip = '用户名不能为空'        
                return false;
            }
            else if (this.username.length >= 5) {
                this.tip = '用户名过长';
                                
                return false;
            }
            else {
                this.tip = '';                
                return true;
            }
        },
        checkWord: function () {
            if (this.pwd == '') {
                this.tip = '密码不能为空';
                return false;
            }
            else if (this.pwd.length < 8) {
                this.tip = '密码应大于8位';
                return false;
            }
            else {
                this.tip = '';                
                return true;
            }
        },
        checkrWord: function () {
            if (this.pwd != this.rpwd) {
                this.tip = '两次输入的密码不一致';
                return false;
            }
            else {
                this.tip = '';
                return true;
            }
        },
        Finish: function () {
            var bug = NULL;
            if (!this.checkName() || !this.checkrWord() || !this.checkWord()) {
                alert("注册失败");
            }
            else {               
                /*axios.get('http://localhost:8080/register/doRegister?', {
                    params: {
                        username: this.username,
                        password: this.pwd
                }
                }).then((response) => {
                    console.log(response.data); 
                    alert("注册成功");
                }).catch(function (error) {
                        console.log(error);
                })*/
                if (bug == NULL) {
                    this.rtip = "";
                }
                else if (bug == 1) {
                    this.rtip = "两次密码不一致";
                }
                else if (bug == 2) {
                    this.rtip = "密码应大于8位";
                }
                else if (bug == 3) {
                    this.rtip = "用户名过长";
                }
                else if (bug == 4) {
                    this.rtip = "用户名已存在";
                }
            }
        },
        
    }
    
})