var data = {
    username: '',
    pwd: '',
    showPwd: false,
}
new Vue({
    el: '#container',
    data: data,
    methods: {
        Finish: function () {
            this.$http.jsonp('http://localhost:8080/', {
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
        }
    }
})