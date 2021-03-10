var data = {
    username: '',
    pwd: '',
    showPwd: false,
    tip:'密码错误',
}
new Vue({
    el: '#container',
    data: data,
    methods: {
        Finish: function () {
            axios.get('', {
                params: {
                    username: this.username,
                    password: this.pwd
                }
            }).then((response) => {
                console.log(response.data);
                alert("登录成功");
            }).catch(function (error) {
                console.log(error);
            })
        },
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
        
    }
})